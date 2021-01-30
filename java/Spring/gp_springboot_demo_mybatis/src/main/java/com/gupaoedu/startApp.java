package com.gupaoedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gupaoedu.mapper")
public class startApp {
    public static void main(String[] args) {
        SpringApplication.run(startApp.class,args);
    }
}
