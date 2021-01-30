package com.gupaoedu;

import com.gupaoedu.controller.UserController;
import com.gupaoedu.pojo.Person;
import com.gupaoedu.pojo.User1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppStarter_ValueAnnotation {
    public static void main(String[] args) {
        //Inject all beans by javaConfig class
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig2.class);
        User1 bean = ac.getBean(User1.class);
        System.out.println(bean);
        System.out.println(bean.getBaiduFile().isOpen());

    }
}
