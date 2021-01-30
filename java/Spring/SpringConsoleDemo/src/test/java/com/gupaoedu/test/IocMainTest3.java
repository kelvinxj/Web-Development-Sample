package com.gupaoedu.test;

import com.gupaoedu.pojo.UserBean;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class IocMainTest3 {

    /**
     * Get bean by static factory
     */
    @Test
    public void func1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-static.xml");
        UserBean user = ac.getBean("user",UserBean.class);
        user.say();
    }

    /**
     * Get bean by dynamic factory
     */
    @Test
    public void func2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-dynamic.xml");
        UserBean user = ac.getBean("user2",UserBean.class);
        user.say();
    }

}
