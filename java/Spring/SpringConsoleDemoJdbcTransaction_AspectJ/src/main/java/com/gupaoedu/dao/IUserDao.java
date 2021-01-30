package com.gupaoedu.dao;

import com.gupaoedu.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {

    public Integer addUser(User user) throws SQLException;

    public Integer addT_User(User user, String password);

    public Integer addT_User_Failed(User user, String password) throws SQLException;

    public List<User> query();
}
