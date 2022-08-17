package com.xjt.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.xjt"}) //指定扫描位置
@MapperScan("com.xjt.educms.mapper")
@SpringBootApplication
public class ClientManageSystemsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientManageSystemsApplication.class,args);
    }
}
