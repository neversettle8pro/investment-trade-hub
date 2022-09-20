package com.proj.service.impl;

import com.google.gson.Gson;
import com.proj.common.CommonResult;
import com.proj.common.ErrorCode;
import com.proj.mapper.ProductMapper;
import com.proj.po.ProductInfo;
import com.proj.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author JerryLiu
 * @Date 2022/9/5 13:29
 * @PackageName:com.proj.service.impl
 * @ClassName: ProductServiceImpl
 * @Description: TODO
 * @Version 1.0
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    ProductInfo productInfo = null;
    @Override
    public CommonResult getProduct(Integer pid) {
        Gson gson = new Gson();
        try {
            productInfo=productMapper.getProduct(pid);
           if (productInfo==null) {
               return CommonResult.fail(ErrorCode.E5061.code(), ErrorCode.E5061.description());
           }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return CommonResult.success(gson.toJson(productInfo));
    }

    @Override
    public CommonResult createProduct(Integer amount, BigDecimal price, BigDecimal rate, String delivery, Date date) {
        try {
            if(price.compareTo(BigDecimal.ZERO)<0) {
                return CommonResult.fail(ErrorCode.E5071.code(), ErrorCode.E5071.description());
            }
            if(amount<0){
                return CommonResult.fail(ErrorCode.E5072.code(), ErrorCode.E5072.description());
            }
            if(delivery.equals("yes")&& Objects.equals(date, null)) {

                return CommonResult.fail(ErrorCode.E5073.code(), ErrorCode.E5073.description());
            }
            productMapper.createProduct(amount, price, rate, delivery, date);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return CommonResult.success("产品创建成功！");
    }

    @Override
    public CommonResult updateProduct(Integer pid, Integer amount, BigDecimal price, BigDecimal rate, String delivery, Date date) {
        try {
            Integer check = productMapper.updateProduct(pid, amount, price, rate, delivery, date);
            if(check == 0) {
                return CommonResult.fail(ErrorCode.E5061.code(), ErrorCode.E5061.description());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return CommonResult.success("产品信息更新成功！");
    }

    @Override
    public CommonResult showProduct() {
        Gson gson = new Gson();
        List<ProductInfo> list;
        list = productMapper.showProduct();
        if(list.isEmpty()) {
            return CommonResult.fail(ErrorCode.E5061.code(), ErrorCode.E5061.description());
        }
        return CommonResult.success(gson.toJson(list));
    }
}
