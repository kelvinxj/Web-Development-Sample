package com.gupaoedu;

import com.gupaoapi.IUserServiceFeign;
import com.gupaoapi.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestUserController {

    @Autowired
    IUserServiceFeign userServiceFeign;

    @GetMapping("/user")
    public String test(){
        User user = new User();
        user.setName("Kelvin");
        return userServiceFeign.insertUser(user);
    }
}
