package com.gupaoedu.controller;

import com.gupaoedu.pojo.User;
import com.gupaoedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//@Component
@Controller
public class UserController {

    //use autowired and qualifier(by bean name)
//    @Autowired //get bean by type, if you need to get bean by name, use @Qualifier with it.
//    @Qualifier("userServiceImpl")
    //or use jdk @Resource annotation(get bean by type and name)
    @Resource(type=com.gupaoedu.service.IUserService.class)
    private IUserService userService;
//    public IUserService getUserService() {
//        return userService;
//    }
//
//    public void setUserService(IUserService userService) {
//        this.userService = userService;
//    }
    public List<User> query(){
        return userService.query();
    }

    public UserController(){
        System.out.println("user controller's default constructor was called...");
    }
}
