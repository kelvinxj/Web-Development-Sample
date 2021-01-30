package com.gupaoedu;

import com.gupaoedu.controller.UserController;
import com.gupaoedu.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppStarter_ConfigClass {
    public static void main(String[] args) {
        //Inject all beans by javaConfig class
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);
        UserController controller = ac.getBean(UserController.class);
        System.out.println(controller.query());
    }
}
