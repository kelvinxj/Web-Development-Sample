package com.gupaoedu.dao.impl;

import com.gupaoedu.dao.IUserDao;
import com.gupaoedu.pojo.User;

import java.util.Arrays;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public List<User> query() {
        return Arrays.asList(
                new User(1,"testUser1")
                , new User(2,"testUser2")
                , new User(3,"Mike"));
    }
}
