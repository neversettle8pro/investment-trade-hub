package com.proj.service.impl;

import com.proj.common.CommonResult;
import com.proj.common.ErrorCode;
import com.proj.mapper.AccountMapper;
import com.proj.mapper.UserMapper;
import com.proj.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.*;
import java.util.List;
import java.util.Objects;

/**
 * @Author JerryLiu
 * @Date 2022/9/2 14:43
 * @PackageName:com.proj.service.impl
 * @ClassName: AccountService
 * @Description: TODO
 * @Version 1.0
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    UserMapper userMapper;
    List<Integer> list = null;
    @Autowired
    RedisTemplate redisTemplate;
    static final String invest = "INVEST";
    static final String checking = "CHECKING";
    @Override
    public CommonResult getAccount(Integer cid) {
        try{
            list=accountMapper.getAccount(cid);
            if(list.isEmpty())
                return CommonResult.fail(ErrorCode.E5051.code(), ErrorCode.E5051.description());
        } catch (Exception e){
            log.error(e.getMessage());
        }
        log.info(String.valueOf(list));
        return CommonResult.success(list.toString());
    }

    @Override
    public CommonResult createChecking(Integer cid) {
        try{
            if(userMapper.search(cid) ==null) {
                return CommonResult.fail(ErrorCode.E5041.code(), ErrorCode.E5041.description());
            }
            accountMapper.createChecking();
            Integer id = accountMapper.newestId();
            accountMapper.buildRelation(cid,id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return CommonResult.success("创建活期账户成功！");
    }

    @Override
    public CommonResult createInvest(Integer cid) {
        try{
            if(accountMapper.getAccount(cid).isEmpty()){
                return CommonResult.fail(ErrorCode.E5056.code(), ErrorCode.E5056.description());
            }
            if(userMapper.search(cid) ==null) {
                return CommonResult.fail(ErrorCode.E5041.code(), ErrorCode.E5041.description());
            }
            accountMapper.createInvest();
            Integer id = accountMapper.newestId();
            accountMapper.buildRelation(cid,id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return CommonResult.success("创建投资账户成功！");
    }

    @Override
    public CommonResult balanceOpt(BigDecimal cash, Integer aid) {
        try {
            if (accountMapper.getAccount(aid)==null) {
                return CommonResult.fail(ErrorCode.E5041.code(), ErrorCode.E5041.description());
            }
            if(accountMapper.checkType(aid).equals(invest)){
                return CommonResult.fail(ErrorCode.E5055.code(), ErrorCode.E5055.description());
            }
            BigDecimal balance=accountMapper.getBalance(aid);
            if (cash.compareTo(BigDecimal.ZERO)==0) {
                return CommonResult.fail(ErrorCode.E5054.code(), ErrorCode.E5054.description());
            }
            if(balance.add(cash).compareTo(BigDecimal.ZERO) < 0) {
                return CommonResult.fail(ErrorCode.E5052.code(), ErrorCode.E5052.description());
            }
            accountMapper.balanceOpt(balance.add(cash),aid);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if(cash.compareTo(BigDecimal.ZERO) < 0) {
            return CommonResult.success("取款成功！");
        } else {
            return CommonResult.success("存款成功！");
        }
    }

    @Override
    public CommonResult transfer(BigDecimal cash, Integer aid2, Integer aid1) {
        try{
            if (accountMapper.getAccount(aid1)==null) {
                return CommonResult.fail(ErrorCode.E5041.code(), ErrorCode.E5041.description());
            }
            if (accountMapper.getAccount(aid2)==null) {
                return CommonResult.fail(ErrorCode.E5041.code(), ErrorCode.E5041.description());
            }
            if((accountMapper.checkType(aid1).equals(invest)) && (accountMapper.checkType(aid2).equals(invest))) {
                return CommonResult.fail(ErrorCode.E5055.code(), ErrorCode.E5055.description());
            }
            if((accountMapper.checkType(aid1).equals(invest)) && (accountMapper.checkType(aid2).equals(checking))||(accountMapper.checkType(aid1).equals(checking)) && (accountMapper.checkType(aid2).equals(invest))) {
                if(!Objects.equals(accountMapper.getCustomer(aid1), accountMapper.getCustomer(aid2))) {
                    return CommonResult.fail(ErrorCode.E5057.code(), ErrorCode.E5057.description());
                }
            }
            BigDecimal balance1=accountMapper.getBalance(aid1);
            BigDecimal balance2=accountMapper.getBalance(aid2);
            if(cash.compareTo(BigDecimal.ZERO) < 0) {
                return CommonResult.fail(ErrorCode.E5053.code(), ErrorCode.E5053.description());
            } else if (cash.compareTo(BigDecimal.ZERO)==0) {
                return CommonResult.fail(ErrorCode.E5054.code(), ErrorCode.E5054.description());
            }
            if(balance2.subtract(cash).compareTo(BigDecimal.ZERO) < 0) {
                return CommonResult.fail(ErrorCode.E5052.code(), ErrorCode.E5052.description());
            }
            accountMapper.balanceOpt(balance1.add(cash),aid1);
            accountMapper.balanceOpt(balance2.subtract(cash),aid2);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return CommonResult.success("转账成功！");
    }
}
