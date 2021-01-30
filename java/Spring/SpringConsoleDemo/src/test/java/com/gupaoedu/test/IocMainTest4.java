package com.gupaoedu.test;

import com.gupaoedu.pojo.Cat;
import com.gupaoedu.pojo.UserBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocMainTest4 {

    /**
     * Inject bean's constructor
     */
    @Test
    public void func1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-01.xml");
        UserBean user = ac.getBean("user",UserBean.class);
        System.out.println(user);
    }

    /**
     * Inject bean's property
     * simple value
     */
    @Test
    public void func2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-01.xml");
        UserBean user = ac.getBean("user1",UserBean.class);
        System.out.println(user);
    }

    /**
     * Inject bean's property:
     * set, map, list
     */
    @Test
    public void func3(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-02.xml");
        UserBean user = ac.getBean("user1",UserBean.class);
        System.out.println(user.getCat());

        String[] favourites = user.getFavourites();
        for(String f: favourites){
            System.out.println(f);
        }
        System.out.println(user.getCats());
        System.out.println(user.getMap());
        System.out.println(user.getProps());
    }
}
