package com.gupaoedu;

import com.gupaoedu.pojo.User;
import com.gupaoedu.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMain {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);

        IUserService service = ac.getBean(IUserService.class);

        service.addUser(new User());
    }
}
