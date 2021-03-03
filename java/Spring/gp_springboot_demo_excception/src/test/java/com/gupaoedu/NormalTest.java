package com.gupaoedu;

import com.gupaoedu.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes={DemoApplication.class})
public class MyTest {

    @Autowired
    IUserService service;

    /**
     * Unit test not run with:
     * @SpringBootTest will not get IoC container
     */
    @Test
    public void test1(){
        System.out.println(service.query());
    }

    @Test
    public void normalTest(){
        Assertions.assertEquals(1,1);
    }

}
