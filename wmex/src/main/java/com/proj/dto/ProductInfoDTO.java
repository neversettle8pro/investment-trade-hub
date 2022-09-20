package com.proj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author JerryLiu
 * @Date 2022/9/5 15:02
 * @PackageName:com.proj.dto
 * @ClassName: ProductInfoDTO
 * @Description: TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoDTO {
    private Integer pid;
    private Integer amount;
    private BigDecimal price;
    private BigDecimal rate;
    private String delivery;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;
}
