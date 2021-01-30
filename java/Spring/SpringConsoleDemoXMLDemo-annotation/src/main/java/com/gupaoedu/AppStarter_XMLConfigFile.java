package com.gupaoedu;

import com.gupaoedu.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppStarter_XMLConfigFile {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserController controller = ac.getBean(UserController.class);
        System.out.println(controller.query());
    }
}
