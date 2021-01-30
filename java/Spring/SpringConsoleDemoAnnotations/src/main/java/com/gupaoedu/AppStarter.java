package com.gupaoedu;

import com.gupaoedu.pojo.Student;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppStarter {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(javaConfig.class);
//        Student bean = ac.getBean(Student.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanName: beanDefinitionNames){
            System.out.println(beanName);
        }
    }
}
