package com.gupaoedu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppStarter {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String name: beanDefinitionNames){
            System.out.println(name);
        }
    }
}
