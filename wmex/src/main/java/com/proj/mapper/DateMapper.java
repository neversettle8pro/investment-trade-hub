package com.proj.mapper;

import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @Author JerryLiu
 * @Date 2022/9/5 11:06
 * @PackageName:com.proj.mapper
 * @InterfaceName: DateMapper
 * @Description: TODO
 * @Version 1.0
 */
@Repository
public interface DateMapper {
    Integer toTomorrow(Date day);
    Date getToday();
}
