package com.proj.controller;

import com.proj.common.CommonResult;
import com.proj.dto.UserDTO;
import com.proj.dto.UserInfoDTO;
import com.proj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author JerryLiu
 * @Date 2022/8/31 14:11
 * @PackageName:com.proj.controller
 * @ClassName: LoginController
 * @Description: TODO
 * @Version 1.0
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    @ResponseBody
    public CommonResult login (@RequestBody UserDTO userDTO){
        return userService.login(userDTO.getPhone(), userDTO.getPassword());
    }

    @PostMapping("/user/logout")
    @ResponseBody
    public CommonResult logout (@RequestBody UserDTO userDTO){
        return userService.logout(userDTO.getCid());
    }

    @GetMapping("/user/information")
    @ResponseBody
    public CommonResult getInfo(@RequestBody UserInfoDTO userInfoDTO){
        return userService.getInfo(userInfoDTO.getCid());
    }

    @PostMapping("/user/updateInfo")
    @ResponseBody
    public CommonResult updateInfo (@RequestBody UserInfoDTO userInfoDTO){
        return userService.updateInfo(userInfoDTO.getCid(), userInfoDTO.getName(), userInfoDTO.getAge());
    }

    @PostMapping("/user/register")
    @ResponseBody
    public CommonResult registerSave (@RequestBody UserDTO userDTO){
        return userService.registerSave(userDTO.getPhone(), userDTO.getPassword());
    }

}
