package com.gupaoedu.dao.impl;

import com.gupaoedu.dao.IUserDao;
import com.gupaoedu.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

//@Component
@Repository
public class UserDaoImpl2 implements IUserDao {
    @Override
    public List<User> query() {
        return Arrays.asList(
                new User(4,"testUser5")
                , new User(5,"testUser6")
                , new User(6,"MikeLo"));
    }
}
