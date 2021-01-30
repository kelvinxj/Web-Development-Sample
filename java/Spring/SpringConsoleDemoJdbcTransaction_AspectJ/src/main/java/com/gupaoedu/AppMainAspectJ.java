package com.gupaoedu;

import com.gupaoedu.pojo.User;
import com.gupaoedu.service.IUserService;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Random;

public class AppMainAspectJ {
    public static void main(String[] args) throws SQLException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);

        IUserService bean = ac.getBean(IUserService.class);

        Random random = new Random();
        int userSuffice = random.nextInt();
        String userName = "toto1" + userSuffice;
        System.out.println("User name: " +
                userName);
        User user = new User();
        user.setName(userName);
        user.setAge(random.nextInt(50));

        bean.business(user,String.valueOf(userSuffice));
    }
}
