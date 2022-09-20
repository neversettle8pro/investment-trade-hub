package com.proj.controller;

import com.proj.common.CommonResult;
import com.proj.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author JerryLiu
 * @Date 2022/9/5 11:18
 * @PackageName:com.proj.controller
 * @ClassName: DateController
 * @Description: TODO
 * @Version 1.0
 */
@Controller
public class DateController {
    @Autowired
    private DateService dateService;
    @GetMapping("/toTomorrow")
    @ResponseBody
    public CommonResult toTomorrow() {
        return dateService.toTomorrow();
    }
}
