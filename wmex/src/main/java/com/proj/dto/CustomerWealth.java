package com.proj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author JerryLiu
 * @Date 2022/9/14 15:45
 * @PackageName:com.proj.dto
 * @ClassName: CustomerWealth
 * @Description: TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWealth {
    private Integer aid;
    private BigDecimal totalWealth;
    private BigDecimal totalBalance;
    private BigDecimal totalProduct;
}
