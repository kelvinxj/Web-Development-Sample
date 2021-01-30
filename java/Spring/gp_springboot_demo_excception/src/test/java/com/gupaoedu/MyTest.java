package com.gupaoedu;

import com.gupaoedu.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}
