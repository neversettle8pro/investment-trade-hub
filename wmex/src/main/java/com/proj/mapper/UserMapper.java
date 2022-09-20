package com.proj.mapper;

import com.proj.po.CustomerInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author JerryLiu
 * @Date 2022/8/31 14:09
 * @PackageName:com.proj.mapper
 * @InterfaceName: LoginMapper
 * @Description: TODO
 * @Version 1.0
 */
@Repository
public interface UserMapper {
    Integer login(@Param("phone") String phone, @Param("password") String password);

    CustomerInfo search(@Param("cid")Integer cid);

    Integer updateInfo(@Param("cid") Integer cid, @Param("name") String name, @Param("age") Integer age);

    boolean registerSave(@Param("phone") String phone, @Param("password") String password);
}
