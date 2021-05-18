package com.gupaoedu.vip.service;

import com.gupaoedu.vip.model.User;

import java.util.List;

public interface IUserService {

    public List<User> query();

    public User save(User user);
}
