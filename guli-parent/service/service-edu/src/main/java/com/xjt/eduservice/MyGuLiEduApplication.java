package com.xjt.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableDiscoveryClient          //在客户端微服务启动类中添加注解
@EnableFeignClients         //feign调用
@ComponentScan("com.xjt")   //默认扫描启动类所在包及其子包（这里我们让它扫描所有名称为 com.xjt 的包）
@SpringBootApplication
public class MyGuLiEduApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyGuLiEduApplication.class,args);
    }
}
