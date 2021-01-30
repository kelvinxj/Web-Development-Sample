package com.gupaoedu;

import com.gupaoedu.JavaConfig2;
import com.gupaoedu.pojo.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppStarter_OtherAnnotation {
    public static void main(String[] args) {
        //Inject all beans by javaConfig class
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig2.class);
        System.out.println("Spring IOC initialized completed...");
        ac.getBean(Person.class);
    }
}
