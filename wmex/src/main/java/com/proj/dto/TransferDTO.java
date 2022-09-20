package com.proj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author JerryLiu
 * @Date 2022/9/14 18:03
 * @PackageName:com.proj.dto
 * @ClassName: transferDTP
 * @Description: TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferDTO {
    private BigDecimal cash;
    private Integer sourceAccountId;
    private Integer destinationAccountId;
}
