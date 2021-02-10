package com.gupaoedu.controller;

import com.gupaoedu.pojo.User;
import com.gupaoedu.service.IUserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

//@Component
@Controller
public class UserController_NoAutowired {

    private IUserService userService;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public List<User> query(){
        return userService.query();
    }

    public UserController_NoAutowired(){
        System.out.println("user controller's default constructor was called...");
    }
}
