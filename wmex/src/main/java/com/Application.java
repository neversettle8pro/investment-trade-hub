package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author JerryLiu
 * @Date 2022/8/30 15:28
 * @PackageName:com
 * @ClassName: Application
 * @Description: TODO
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.proj.mapper")
@EnableTransactionManagement
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
