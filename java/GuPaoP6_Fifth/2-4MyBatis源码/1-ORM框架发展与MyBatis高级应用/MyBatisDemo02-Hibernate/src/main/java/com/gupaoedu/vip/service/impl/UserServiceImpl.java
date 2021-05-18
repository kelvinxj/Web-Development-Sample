package com.gupaoedu.vip.service.impl;


import com.gupaoedu.vip.dao.IUserDao;
import com.gupaoedu.vip.model.User;
import com.gupaoedu.vip.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao dao;

    @Override
    public List<User> query() {
        return dao.findAll();
    }

    @Override
    public User save(User user) {
        return dao.save(user);
    }
}
