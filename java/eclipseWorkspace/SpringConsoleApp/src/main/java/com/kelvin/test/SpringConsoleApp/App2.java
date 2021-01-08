package com.kelvin.test.SpringConsoleApp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kelvin.test.SpringConsoleApp.beans.HelloWorld;
import com.kelvin.test.SpringConsoleApp.beans.MyBean;
import com.kelvin.test.SpringConsoleApp.components.AppComponent;
import com.kelvin.test.SpringConsoleApp.config.AppConfiguration;
import com.kelvin.test.SpringConsoleApp.config.TestConfiguration;
import com.kelvin.test.SpringConsoleApp.controllers.AppController;
import com.kelvin.test.SpringConsoleApp.services.AppService;

public class App2 {

	public static void main(String[] args) {
		//load configuration class method1:
    	ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class, AppConfiguration.class);
    	
    	//get all beans registered by Configuration class
//    	String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for (String beanName : beanDefinitionNames) {
//            System.out.println("beanName: " + beanName);
//        }
    	
    	HelloWorld myBean = context.getBean(HelloWorld.class);
    	System.out.println(myBean.getMessage());
    	
    	//get appController bean
    	AppController appController = context.getBean(AppController.class);
    	System.out.println(appController);
    	
    	//get service bean
    	AppService appService = context.getBean(AppService.class);
    	System.out.println(appService);
    	
    	//get component bean
    	AppComponent appComponent = context.getBean(AppComponent.class);
    	System.out.println(appComponent);
    	
    	//this bean is defined in beans.xml
    	MyBean myBean1 = context.getBean(MyBean.class);
    	System.out.println(myBean1.getName());
    	System.out.println(myBean1.getAppComponent());
    	System.out.println(myBean1.getMyBean1Interface().getName());
	}

}
