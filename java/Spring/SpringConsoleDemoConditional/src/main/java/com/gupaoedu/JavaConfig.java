package com.gupaoedu;

import com.gupaoedu.conditional.ConditionalOnBean;
import com.gupaoedu.conditional.ConditionalOnClass;
import com.gupaoedu.pojo.Person;
import com.gupaoedu.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    public Person person(){
        return new Person();
    }

    @Bean
    //it means a conditional annotation.
    //load this bean only when ConditionalOnBean's matches method return true
    //@Conditional(ConditionalOnBean.class)
    @Conditional(ConditionalOnClass.class)
    public User getUser(){
        return new User();
    }
}
