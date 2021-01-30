package com.gupaoedu.service;

import com.gupaoedu.pojo.User;

import java.util.List;

public interface IUserService {
    public Integer addUser(User user);

    public List<User> query();

    public Integer addT_User(User user, String password);

    public Integer addT_User_Failed(User user, String password);
}
