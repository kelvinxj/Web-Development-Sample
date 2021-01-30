package com.gupaoedu.service.impl;

import com.gupaoedu.dao.IUserDao;
import com.gupaoedu.pojo.User;
import com.gupaoedu.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {

    public IUserDao getDao() {
        return dao;
    }

    public void setDao(IUserDao dao) {
        this.dao = dao;
    }

    private IUserDao dao;

    @Override
    public List<User> query() {
        return dao.query();
    }
}
