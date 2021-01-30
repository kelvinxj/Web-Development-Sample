package com.gupaoedu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestHelloController {

    @Autowired
    IHelloControllerFeign iHelloControllerFeign;

    @GetMapping("/hellotest")
    public String test(){
        return iHelloControllerFeign.sayHello();
    }
}
