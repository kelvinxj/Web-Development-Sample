package com.gupaoedu.dao.impl;

import com.gupaoedu.dao.IUserDao;
import com.gupaoedu.pojo.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

//@Component
@Repository
//if two beans with same type found, @Primary will be choosed first
@Primary
public class UserDaoImpl implements IUserDao {
    @Override
    public List<User> query() {
        return Arrays.asList(
                new User(1,"testUser1")
                , new User(2,"testUser2")
                , new User(3,"Mike"));
    }
}
