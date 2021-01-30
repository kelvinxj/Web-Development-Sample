package com.gupaoedu;

import com.gupaoedu.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class GpSpringBootApplication {

    public static void main(String[] args) {
        //ApplicationContext ac = SpringApplication.run(GpSpringBootApplication.class, args);
        //close banner
        SpringApplication app = new SpringApplication(GpSpringBootApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }
}
