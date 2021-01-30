package com.gupaoedu.service.impl;

import com.gupaoedu.dao.IUserDao;
import com.gupaoedu.dao.impl.UserDaoImpl;
import com.gupaoedu.pojo.User;
import com.gupaoedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
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
    @Transactional()
    public void business() throws Exception {
        User user = new User("zhangsan",24);
        addUser(user);

        user.setId(1);
        Random random = new Random();
        int suffix = random.nextInt(1000);
        user.setName("abc-changed" + suffix);
        updateUser(user);
    }

    @Override
    public Integer updateUser(User user) throws Exception {
        return dao.updateUser(user);
    }
}
