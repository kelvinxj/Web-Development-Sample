package com.gupaoedu.vip.springjdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JdbcTemplateTest {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserDao dao = ac.getBean(UserDao.class);
        dao.query1();
        System.out.println("-----");
        dao.query2();
    }
}
