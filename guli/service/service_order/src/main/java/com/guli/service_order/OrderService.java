package com.guli.service_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 刘佳俊
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.service_base","com.commonutils"})
@EnableDiscoveryClient
public class OrderService {
    public static void main(String[] args) {
        SpringApplication.run(OrderService.class,args);
    }
}
