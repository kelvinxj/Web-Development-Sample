package com.gupaoedu.service.impl;

import com.gupaoedu.annotation.GuPaoTx;
import com.gupaoedu.dao.IUserDao;
import com.gupaoedu.dao.impl.UserDaoImpl;
import com.gupaoedu.pojo.User;
import com.gupaoedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    //@Autowired
    private IUserDao dao = new UserDaoImpl();

    @Override
    public Integer addUser(User user) throws SQLException {
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
    public Integer addT_User_Failed(User user, String password) throws SQLException {
        return dao.addT_User_Failed(user, password);
    }

    @Override
    @GuPaoTx
    public Integer business(User user, String password) throws SQLException {
        dao.addUser(user);
        dao.addT_User_Failed(user, password);
        return 0;
    }
}
