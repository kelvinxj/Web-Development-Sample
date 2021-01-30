package com.gupaoedu.test;

import com.gupaoedu.pojo.UserBean;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class IocMainTest2 {

    /**
     * ApplicationContext
     * will instantiate bean(by calling its default constructor) when initialize IoC container.
     */
    @Test
    public void func1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    /**
     * BeanFactory
     * will NOT instantiate bean when initialize IoC container.
     * instead, will instantiate bean when get bean object
     */
    @Test
    public void func2(){
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        factory.getBean("u1");
    }
}
