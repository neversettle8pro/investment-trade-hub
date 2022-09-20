package com.proj.service;

import com.proj.common.CommonResult;

/**
 * @Author JerryLiu
 * @Date 2022/8/31 14:07
 * @PackageName:com.proj.service
 * @InterfaceName: LoginService
 * @Description: TODO
 * @Version 1.0
 */
public interface UserService {
    CommonResult login(String phone,String password);
    CommonResult getInfo(Integer cid);
    CommonResult registerSave(String phone, String password);
    CommonResult updateInfo(Integer cid,String name,Integer age);
    CommonResult logout(Integer cid);
}
