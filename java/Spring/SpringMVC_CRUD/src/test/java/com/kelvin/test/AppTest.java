package com.kelvin.test;

import com.kelvin.service.IUserService;
import jdk.nashorn.internal.runtime.regexp.joni.ApplyCaseFoldArg;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {

    @Test
    public void testQuery(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        IUserService service = ac.getBean(IUserService.class);
        System.out.println(service.queryList());
    }
}
