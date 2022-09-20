package com.proj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author JerryLiu
 * @Date 2022/9/1 18:11
 * @PackageName:com.proj.dto
 * @ClassName: LoginDTO
 * @Description: TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer cid;
    private String phone;
    private String password;
}
