package com.gupaoedu.demo02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableGpDefine
public class ImportMain {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ImportMain.class);
        final String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String name: beanDefinitionNames){
            System.out.println(name);
        }
    }
}
