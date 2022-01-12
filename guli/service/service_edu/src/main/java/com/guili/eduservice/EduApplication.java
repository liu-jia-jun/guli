package com.guili.eduservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 刘佳俊
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.service_base","com.guili","com.commonutils"})
@MapperScan(basePackages = {"com.guili.eduservice.mapper"})
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
