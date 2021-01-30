package com.gupaoedu;

import com.gupaoedu.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {
    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //this should fail as wrong column name in dao.updateUser(). and no data will be inserted to DB.
        IUserService bean = ac.getBean(IUserService.class);
        bean.business();
    }
}
