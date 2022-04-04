package com.guli.service_oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 刘佳俊
 *
 * exclude = DataSourceAutoConfiguration.class
 *
 * 在oss服务这个模块中继承了service父模块中的数据库依赖，但是在该模块中的配置文件中没有数据库配置
 *
 * 我们可以在@SpringBootApplication注解中添加 exclude 值，此时在该服务启动时不会去加载数据库的部分
 *
 *
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.service_base","com.commonutils","com.guli.service_oss"})
@EnableDiscoveryClient
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}
