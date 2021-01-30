package com.gupaoedu.dao;

import com.gupaoedu.pojo.User;

import java.util.List;

public interface IUserDao {

    public Integer addUser(User user);

    public Integer addT_User(User user, String password);

    public Integer addT_User_Failed(User user, String password);

    public List<User> query();
}
