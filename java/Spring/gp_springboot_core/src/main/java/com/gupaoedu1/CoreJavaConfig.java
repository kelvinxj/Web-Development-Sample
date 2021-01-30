package com.gupaoedu1;

import com.gupaoedu1.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreJavaConfig {
    @Bean
    public UserService userService(){
        return new UserService();
    }
}
