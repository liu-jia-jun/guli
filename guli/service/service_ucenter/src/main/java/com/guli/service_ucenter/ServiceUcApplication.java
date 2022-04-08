package com.guli.service_ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 刘佳俊
 */
@ComponentScan({"com.guli","com.service_base"})
@SpringBootApplication//取消数据源自动配置
//@MapperScan("com.atguigu.ucenterservice.mapper")
public class ServiceUcApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUcApplication.class, args);
    }
}