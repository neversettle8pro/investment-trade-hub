package com.proj.service.impl;

import com.google.gson.Gson;
import com.proj.common.CommonResult;
import com.proj.common.ErrorCode;
import com.proj.mapper.AccountMapper;
import com.proj.po.CustomerInfo;
import com.proj.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author JerryLiu
 * @Date 2022/8/31 14:08
 * @PackageName:com.proj.service.impl
 * @ClassName: LoginServiceImpl
 * @Description: TODO
 * @Version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements com.proj.service.UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String passEx = "^(?=.*\\d)(?=.*[a-zA-Z])(.{8,15})$";
    private static final String phoneEx = "^(1[3-9])\\d{9}$";
    @Override
    public CommonResult login(String phone,String password){

        Gson gson = new Gson();
        String secret = Base64.getEncoder().encodeToString(password.getBytes());
        Integer cid;
        List<Integer> list = null;
        if(phone.equals("")||password.equals("")){
            return CommonResult.fail(ErrorCode.E5021.code(), ErrorCode.E5021.description());
        }
        try {
            cid= userMapper.login(phone,secret);
        } catch (Exception e) {
            return CommonResult.fail(ErrorCode.E5021.code(), ErrorCode.E5021.description());
        }
        try {
            list = accountMapper.getAccount(cid);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        redisTemplate.opsForValue().set(cid.toString(),gson.toJson(list));//序列化
        redisTemplate.expire(cid.toString(),10, TimeUnit.MINUTES);
        log.info(redisTemplate.opsForValue().size(cid.toString())+" "+redisTemplate.opsForValue().get(cid.toString()));
        return CommonResult.success("登录成功！cid:"+cid);
    }
    @Override
    public CommonResult logout(Integer cid) {
        redisTemplate.opsForValue().getAndDelete(cid.toString());
        return CommonResult.success("登出成功！cid:"+cid);
    }
    @Override
    public CommonResult getInfo(Integer cid){
        Gson gson = new Gson();
        CustomerInfo userInfo = null;
        try{
            userInfo= userMapper.search(cid);
            if(userInfo==null){
                return CommonResult.fail(ErrorCode.E5051.code(), ErrorCode.E5051.description());
            }
            if(userInfo.getName()==null||userInfo.getAge()==null) {
                return CommonResult.fail(ErrorCode.E5031.code(), ErrorCode.E5031.description());
            }
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return CommonResult.success(gson.toJson(userInfo));
    }

    @Override
    public CommonResult updateInfo(Integer cid,String name,Integer age){
        int uplines = 0;
        try {
            uplines=userMapper.updateInfo(cid, name, age);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        if(uplines==0){
            return CommonResult.fail(ErrorCode.E5041.code(), ErrorCode.E5041.description());
        }
        return CommonResult.success("更新信息成功！");
    }


    @Override
    public CommonResult registerSave(String phone, String password){
        if(StringUtils.hasText(phone)) {
            return CommonResult.fail(ErrorCode.E5011.code(), ErrorCode.E5011.description());
        }
        else if (!phone.matches(phoneEx)) {
            return CommonResult.fail(ErrorCode.E5012.code(), ErrorCode.E5012.description());
        }
        if(StringUtils.hasText(password)) {
            return CommonResult.fail(ErrorCode.E5013.code(), ErrorCode.E5013.description());
        }
        else if(!password.matches(passEx)) {
            return CommonResult.fail(ErrorCode.E5014.code(), ErrorCode.E5014.description());
        }
        String secret = Base64.getEncoder().encodeToString(password.getBytes());
        try{
            userMapper.registerSave(phone, secret);
        }catch(Exception e){
            return CommonResult.fail(ErrorCode.E5015.code(), ErrorCode.E5015.description());
        }
        return CommonResult.success("注册成功！");
    }
}
