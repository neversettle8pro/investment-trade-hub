package com.proj.dto;

import com.proj.po.AccountInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author JerryLiu
 * @Date 2022/9/2 10:49
 * @PackageName:com.proj.dto
 * @ClassName: UserInfoDTO
 * @Description: TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAssetDTO {
    private List<CustomerWealth> cWealth;
    private BigDecimal totalCash;
    private BigDecimal totalAsset;
}
