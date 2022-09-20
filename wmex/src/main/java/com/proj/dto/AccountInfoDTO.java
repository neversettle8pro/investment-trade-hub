package com.proj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author JerryLiu
 * @Date 2022/9/2 15:06
 * @PackageName:com.proj.dto
 * @ClassName: AccountInfoDTO
 * @Description: TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfoDTO {
    private Integer cid;
    private Integer aid;
    private String type;
    private BigDecimal balance;
    private BigDecimal cash;
}
