package com.proj.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author JerryLiu
 * @Date 2022/8/31 10:25
 * @PackageName:com.proj.common
 * @ClassName: CommonResult
 * @Description: TODO
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {
    /**
     * 响应码状态
     */
    private int status;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 返回成功方法
     */
    public static  CommonResult success(String msg){
        return new CommonResult(200,msg);
    }

    public static  CommonResult success(){
        return new CommonResult(200,"请求成功");
    }
    /**
     * 返回错误方法
     */
    public static CommonResult fail(int code,String msg){
        return new CommonResult(code,msg);
    }
}
