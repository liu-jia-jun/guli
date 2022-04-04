package com.guli.service_edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 刘佳俊
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.service_base","com.guli","com.commonutils"})
@MapperScan(basePackages = {"com.guli.eduservice.mapper"})
@EnableDiscoveryClient//服务注册
@EnableFeignClients
@EnableHystrix
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
