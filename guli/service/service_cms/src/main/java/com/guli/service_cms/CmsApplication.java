package com.guli.service_cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 刘佳俊
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.service_base","com.guli.service_cms","com.commonutils"}) //指定扫描位置
@MapperScan("com.guli.service_cms.mapper")
@EnableDiscoveryClient
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}