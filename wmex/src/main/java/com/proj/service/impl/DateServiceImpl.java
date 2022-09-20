package com.proj.service.impl;

import com.proj.common.CommonResult;
import com.proj.mapper.DateMapper;
import com.proj.mapper.ProductMapper;
import com.proj.po.ProductInfo;
import com.proj.service.DateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author JerryLiu
 * @Date 2022/9/5 11:19
 * @PackageName:com.proj.service.impl
 * @ClassName: DateServiceImpl
 * @Description: TODO
 * @Version 1.0
 */
@Slf4j
@Service
public class DateServiceImpl implements DateService {
    @Autowired
    DateMapper dateMapper;
    @Autowired
    ProductMapper productMapper;
    @Override
    public CommonResult toTomorrow() {
        try {
            Date day = dateMapper.getToday();
            log.info(day.toString());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(day);
            calendar.add(Calendar.DATE,1);
            day = calendar.getTime();
            log.info(String.valueOf(day));
            dateMapper.toTomorrow(day);
            List<ProductInfo> list = productMapper.newDayGetInfo();
            list.forEach(o->{
                BigDecimal newPrice = o.getPrice().multiply(BigDecimal.ONE.add(o.getRate()));
                productMapper.newDaySetInfo(o.getPid(),newPrice);
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return CommonResult.success("新的一天开始了！");
    }
}
