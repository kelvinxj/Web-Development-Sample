package com.gupaoedu.service;

import com.gupaoedu.pojo.User;

import java.util.List;

public interface IUserService {

    public List<User> query(User user);

    public Integer addUser(User user);

    public User queryById(Integer id);

    public Integer updateUser(User user);

    public Integer deleteUser(Integer id);
}
