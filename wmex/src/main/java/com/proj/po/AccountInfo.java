package com.proj.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.*;

/**
 * @Author JerryLiu
 * @Date 2022/9/2 15:03
 * @PackageName:com.proj.po
 * @ClassName: AccountInfo
 * @Description: TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {
    private Integer cid;
    private Integer aid;
    private String type;
    private BigDecimal balance;
}
