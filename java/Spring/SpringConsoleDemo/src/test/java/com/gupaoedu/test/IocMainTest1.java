package com.gupaoedu.test;

import com.gupaoedu.pojo.UserBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocMainTest1 {

    @Test
    public void func1(){
        //Non IOC get bean
        UserBean user = new UserBean();
        user.say();
    }

    @Test
    /**
     * get Bean by id
     */
    public void func2(){
        //1. IoC container initialize. Will call default(no parameter)constructor to instansiate object.
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2. get uerBean object from IoC container
        UserBean user = (UserBean)ac.getBean("userBean1");
        user.say();
    }

    @Test
    /**
     * get Bean by name
     */
    public void func3(){
        //1. IoC container initialize. Will call default(no parameter)constructor to instansiate object.
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2. get uerBean object from IoC container
        UserBean user = (UserBean)ac.getBean("userBean2");
        user.say();
    }

    @Test
    /**
     * get Bean by id
     */
    public void func4(){
        //1. IoC container initialize. Will call default(no parameter)constructor to instansiate object.
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2. get uerBean object from IoC container
        UserBean user = (UserBean)ac.getBean("user1,user2,user3");
        user.say();
    }

    @Test
    /**
     * get Bean by name(a part of bean name)
     */
    public void func5(){
        //1. IoC container initialize. Will call default(no parameter)constructor to instansiate object.
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2. get uerBean object from IoC container
        UserBean user = (UserBean)ac.getBean("u2");
        user.say();
    }

    @Test
    /**
     * get Bean by class and name.
     * If multiple bean with same class found, specify both name and class
     */
    public void func6(){
        //1. IoC container initialize. Will call default(no parameter)constructor to instansiate object.
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2. get uerBean object from IoC container
        //UserBean user = ac.getBean(UserBean.class);

        UserBean user = ac.getBean("u1",UserBean.class);
        user.say();
    }

    @Test
    /**
     * get Bean by class and name.
     * If multiple bean with same class found, specify both name and class
     * If a bean marked with primary="true", will matched first.
     */
    public void func7(){
        //1. IoC container initialize. Will call default(no parameter)constructor to instansiate object.
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2. get uerBean object from IoC container
        //UserBean user = ac.getBean(UserBean.class);

        UserBean user = ac.getBean(UserBean.class);
        user.say();
    }
}
