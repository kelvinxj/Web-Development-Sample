package com.gupaoedu;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        System.out.println("Received request.");
        return "Hello, Kelvin";
    }
}
