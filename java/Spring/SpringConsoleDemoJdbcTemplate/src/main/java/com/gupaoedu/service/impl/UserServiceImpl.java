package com.gupaoedu.service.impl;

import com.gupaoedu.dao.IUserDao;
import com.gupaoedu.pojo.User;
import com.gupaoedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao dao;

    @Override
    public Integer addUser(User user) {
        return dao.addUser(user);
    }

    @Override
    public List<User> query() {
        return dao.query();
    }

    @Override
    public Integer addT_User(User user, String password) {
        return dao.addT_User(user, password);
    }

    @Override
    public Integer addT_User_Failed(User user, String password) {
        return dao.addT_User_Failed(user, password);
    }
}
