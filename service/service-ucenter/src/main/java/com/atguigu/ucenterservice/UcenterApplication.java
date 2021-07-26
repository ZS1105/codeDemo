package com.atguigu.ucenterservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Author: shuai
 * Date: 2021/7/19
 * desc:
 */
@SpringBootApplication//取消数据源自动配置
@ComponentScan({"com.atguigu"})
@EnableDiscoveryClient
@MapperScan("com.atguigu.ucenterservice.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
