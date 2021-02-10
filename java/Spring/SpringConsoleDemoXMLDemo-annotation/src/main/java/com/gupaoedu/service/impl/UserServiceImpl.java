package com.gupaoedu.service.impl;

import com.gupaoedu.dao.IUserDao;
import com.gupaoedu.pojo.User;
import com.gupaoedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

//@Component
@Service
//default bean is class name but first letter lower case: userServiceImpl
//equals to : <bean class="com.gupaoedu.service.impl.UserServiceImpl" name="userServiceImpl"/>
public class UserServiceImpl implements IUserService {

//    public IUserDao getDao() {
//        return dao;
//    }
//
//    public void setDao(IUserDao dao) {
//        this.dao = dao;
//    }

    @Autowired
    private IUserDao dao;

    @Override
    public List<User> query() {
        return dao.query();
    }
}
