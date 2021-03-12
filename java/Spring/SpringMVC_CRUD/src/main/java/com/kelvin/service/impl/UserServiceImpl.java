package com.kelvin.service.impl;

import com.kelvin.bean.User;
import com.kelvin.mapper.UserMapper;
import com.kelvin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryList() {
        return userMapper.queryList();
    }
}
