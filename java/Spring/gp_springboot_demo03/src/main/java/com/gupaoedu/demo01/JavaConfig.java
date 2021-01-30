package com.gupaoedu.demo01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({User.class})
public class JavaConfig {

//    @Bean
//    public User user(){
//        return new User();
//    }
}
