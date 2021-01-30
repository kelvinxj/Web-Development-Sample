package com.gupaoedu;

import com.gupaoedu1.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GpSpringbootDemo03Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(GpSpringbootDemo03Application.class, args);
        System.out.println(run.getBean(UserService.class));
    }

}
