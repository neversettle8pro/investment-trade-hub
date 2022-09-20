package com.proj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class UserInfoDTO {
    private Integer cid;
    private String name;
    private Integer age;
}
