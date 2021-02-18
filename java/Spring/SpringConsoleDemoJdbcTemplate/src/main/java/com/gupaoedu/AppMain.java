package com.gupaoedu;

import com.gupaoedu.pojo.User;
import com.gupaoedu.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMain {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);

        IUserService service = ac.getBean(IUserService.class);

        User user = new User();
        user.setName("user1");
        user.setAge(18);
        int key = service.addUser(user);

        System.out.println(user.toString() + "; id=" + key);
    }
}
