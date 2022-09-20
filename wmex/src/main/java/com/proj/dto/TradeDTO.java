package com.proj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * @Author JerryLiu
 * @Date 2022/9/5 16:30
 * @PackageName:com.proj.dto
 * @ClassName: TradeDTO
 * @Description: TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeDTO {
    private Integer cid;
    private Integer aid;
    private Integer pid;
    private Integer amount;
    private Date date;
}
