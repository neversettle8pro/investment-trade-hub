package com.proj.service;

import com.proj.common.CommonResult;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author JerryLiu
 * @Date 2022/9/5 13:29
 * @PackageName:com.proj.service
 * @InterfaceName: ProductService
 * @Description: TODO
 * @Version 1.0
 */
public interface ProductService {
    CommonResult getProduct(Integer pid);
    CommonResult createProduct(Integer amount, BigDecimal price, BigDecimal rate, String delivery, Date date);
    CommonResult updateProduct(Integer pid, Integer amount, BigDecimal price, BigDecimal rate, String delivery, Date date);

    CommonResult showProduct();
}
