package com.proj.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author JerryLiu
 * @Date 2022/9/6 16:29
 * @PackageName:com.proj.po
 * @ClassName: TradeInfo
 * @Description: TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeInfo {
    private Integer aid;
    private Integer pid;
    private Integer amount;
}
