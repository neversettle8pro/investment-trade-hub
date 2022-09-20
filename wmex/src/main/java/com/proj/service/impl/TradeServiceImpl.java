package com.proj.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.proj.common.CommonResult;
import com.proj.common.ErrorCode;
import com.proj.dto.CustomerWealth;
import com.proj.dto.UserAssetDTO;
import com.proj.mapper.AccountMapper;
import com.proj.mapper.DateMapper;
import com.proj.mapper.ProductMapper;
import com.proj.mapper.TradeMapper;
import com.proj.po.ProductInfo;
import com.proj.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author JerryLiu
 * @Date 2022/9/5 16:09
 * @PackageName:com.proj.service.impl
 * @ClassName: TradeServiceImpl
 * @Description: TODO
 * @Version 1.0
 */
@Slf4j
@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    private TradeMapper tradeMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private DateMapper dateMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String YES = "yes";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult buy(Integer cid, Integer aid, Integer pid, Integer amount) {
        Gson gson = new Gson();
        List<Integer> list = null;
        try {
            String originalValue = (String) redisTemplate.opsForValue().get(cid.toString());
            list = gson.fromJson(originalValue, new TypeToken<List<Integer>>() {
            }.getType());
            if(CollectionUtils.isEmpty(list)) {
                return CommonResult.fail(ErrorCode.E5066.code(), ErrorCode.E5066.description());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (list.stream().noneMatch(o -> o.equals(aid))) {//redis检测aid所属
            return CommonResult.fail(ErrorCode.E5057.code(), ErrorCode.E5057.description());
        }

        try {
            ProductInfo productInfo = productMapper.getProduct(pid);
            BigDecimal balance = accountMapper.getBalance(aid);
            BigDecimal cash = productInfo.getPrice().multiply(BigDecimal.valueOf(amount));
            if (cash.compareTo(balance) > 0) {//查询余额
                return CommonResult.fail(ErrorCode.E5052.code(), ErrorCode.E5052.description());
            }
            if (productMapper.getAmount(pid) < amount) {//查询产品剩余数量
                return CommonResult.fail(ErrorCode.E5065.code(), ErrorCode.E5065.description());
            }
            tradeMapper.createDeal(aid, pid, amount, productMapper.getPrice(pid), dateMapper.getToday());
            accountMapper.balanceOpt(accountMapper.getBalance(aid).subtract(cash), aid);
            productMapper.changeAmount(productMapper.getAmount(pid) - amount, pid);
        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonResult.success("买入成功！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult sell(Integer cid, Integer aid, Integer pid, Integer amount) {
        try {
            ProductInfo productInfo = productMapper.getProduct(pid);
            if (YES.equals(productInfo.getDelivery())) {
                return CommonResult.fail(ErrorCode.E5062.code(), ErrorCode.E5062.description());
            }
            BigDecimal cash = productInfo.getPrice().multiply(BigDecimal.valueOf(amount));
            if (tradeMapper.getAmount(aid, pid) < amount) {//产品持有数检查  事务控制缺漏
                return CommonResult.fail(ErrorCode.E5063.code(), ErrorCode.E5063.description());
            }
            tradeMapper.createDeal(aid, pid, -amount, productMapper.getPrice(pid), dateMapper.getToday());
            accountMapper.balanceOpt(accountMapper.getBalance(aid).add(cash), aid);
            productMapper.changeAmount(productMapper.getAmount(pid) + amount, pid);
        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

        }
        return CommonResult.success("出售成功！");
    }

    @Override
    public CommonResult count(Integer cid) {
        Gson gson = new Gson();
        UserAssetDTO userAssetDTO = new UserAssetDTO();
        List<CustomerWealth> cList = new ArrayList<>();
        List<Integer> aid = accountMapper.getAccount(cid);
        BigDecimal cash;
        BigDecimal together;
        BigDecimal togeBalance = BigDecimal.ZERO;
        BigDecimal togeProduct = BigDecimal.ZERO;
        try {
            if(aid.isEmpty()) {
                return CommonResult.fail(ErrorCode.E5064.code(), ErrorCode.E5064.description());
            }
            for (int i = 0; i < aid.size(); i++) {
                CustomerWealth customerWealth = new CustomerWealth();

                List<Integer> list = tradeMapper.getPid(aid.get(i));
                cash = BigDecimal.ZERO;
                for (Integer integer : list) {
                    cash = cash.add(BigDecimal.valueOf(tradeMapper.getAmount(aid.get(i), integer)).multiply(productMapper.getPrice(integer)));
                }
                together = cash.add(accountMapper.getBalance(aid.get(i)));
                customerWealth.setAid(aid.get(i));
                customerWealth.setTotalWealth((together.add(cash)));
                customerWealth.setTotalBalance(accountMapper.getBalance(aid.get(i)));
                customerWealth.setTotalProduct(cash);
                cList.add(customerWealth);
                userAssetDTO.setCWealth(cList);
                togeBalance = togeBalance.add(accountMapper.getBalance(aid.get(i)));
                togeProduct = togeProduct.add(cash);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return CommonResult.success(gson.toJson(userAssetDTO));
    }

    @Transactional(rollbackFor = Exception.class)
    public void doSell(int o) {
        try {
            if (dateMapper.getToday().compareTo((productMapper.getProduct((tradeMapper.getInfoById(o)).getPid())).getDate()) == 0) {
                tradeMapper.createDeal(tradeMapper.getInfoById(o).getAid(), tradeMapper.getInfoById(o).getPid(), -tradeMapper.getInfoById(o).getAmount(), productMapper.getPrice(tradeMapper.getInfoById(o).getPid()), dateMapper.getToday());
                accountMapper.balanceOpt(accountMapper.getBalance(tradeMapper.getInfoById(o).getAid()).add(productMapper.getProduct(tradeMapper.getInfoById(o).getPid()).getPrice().multiply(BigDecimal.valueOf(tradeMapper.getInfoById(o).getAmount()))), tradeMapper.getInfoById(o).getAid());
                productMapper.changeAmount(productMapper.getAmount(tradeMapper.getInfoById(o).getPid()) + tradeMapper.getInfoById(o).getAmount(), tradeMapper.getInfoById(o).getPid());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    public CommonResult sellDelivery() {
        List<Integer> list = tradeMapper.getDelivery();
        list.forEach(this::doSell);
        return CommonResult.success("交割产品出售成功！");
    }
}
