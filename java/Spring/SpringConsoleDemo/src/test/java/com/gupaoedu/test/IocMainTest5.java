package com.gupaoedu.test;

import com.gupaoedu.JavaConfig;
import com.gupaoedu.pojo.User;
import javafx.application.Application;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IocMainTest5 {

    @Test
    public void func1(){
        //initialize IOC container by @Configuration annotation
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);
        //get bean by default name(methodName: getUser)
//        System.out.println( ac.getBean("getUser", User.class));
        System.out.println(ac.getBean("user1",User.class));
    }
}
