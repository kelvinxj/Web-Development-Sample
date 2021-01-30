package com.gupaoedu.service.impl;

import com.gupaoedu.mapper.UserMapper;
import com.gupaoedu.pojo.User;
import com.gupaoedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> query(User user) {
        return mapper.query(user);
    }

    @Override
    public Integer addUser(User user) {
        return mapper.addUser(user);
    }

    @Override
    public User queryById(Integer id) {
        return mapper.queryById(id);
    }

    @Override
    public Integer updateUser(User user) {
        return mapper.updateUser(user);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return mapper.deleteUser(id);
    }
}
