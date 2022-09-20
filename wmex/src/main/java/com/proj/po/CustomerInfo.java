package com.proj.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author JerryLiu
 * @Date 2022/9/1 11:02
 * @PackageName:com.proj.entity
 * @ClassName: CustomerInfo
 * @Description: TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfo {
    private Integer cid;
    private String name;
    private Integer age;
}
