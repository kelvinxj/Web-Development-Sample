package com.gupaoedu;

import com.gupaoedu.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java config class,
 * is equal to applicationContext.xml
 */
@Configuration
public class JavaConfig {

    @Bean(name = "user1")
    //@Bean is the same as add <bean> to applicationContext.xml
    //default bean name is method name: getBean("getUser")
    public User getUser(){
        User user = new User();
        return user;
    }
}
