package com.gupaoedu.controller;

import com.gupaoedu.pojo.User;
import com.gupaoedu.service.IUserService;

import java.util.List;

public class UserController {
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
}
