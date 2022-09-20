package com.proj.mapper;

import com.proj.po.ProductInfo;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author JerryLiu
 * @Date 2022/9/5 13:27
 * @PackageName:com.proj.mapper
 * @InterfaceName: ProductMapper
 * @Description: TODO
 * @Version 1.0
 */
@Repository
public interface ProductMapper {
    ProductInfo getProduct(Integer pid);
    Integer createProduct(Integer amount, BigDecimal price, BigDecimal rate, String delivery, Date date);
    Integer updateProduct(Integer pid, Integer amount, BigDecimal price, BigDecimal rate, String delivery, Date date);
    List<ProductInfo> newDayGetInfo();
    void newDaySetInfo(Integer pid, BigDecimal price);
    void changeAmount(Integer amount, Integer pid);
    Integer getAmount(Integer pid);
    BigDecimal getPrice(Integer pid);
    List<ProductInfo> showProduct();
}
