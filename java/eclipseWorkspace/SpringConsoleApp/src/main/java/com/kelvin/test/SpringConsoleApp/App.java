package com.kelvin.test.SpringConsoleApp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kelvin.test.SpringConsoleApp.beans.HelloWorld;
import com.kelvin.test.SpringConsoleApp.beans.MyBean;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    	
    	//1. get bean by id: <bean id="helloWorldBean"
    	//HelloWorld myBean = (HelloWorld)context.getBean("helloWorldBean");
    	
    	//2. get bean by class
    	MyBean myBean = context.getBean(MyBean.class);
    	System.out.println(myBean.getName());
    }
}
