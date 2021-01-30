package com.gupaoedu.service;

import com.gupaoedu.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    public Integer addUser(User user) throws SQLException;

    public List<User> query();

    public Integer addT_User(User user, String password);

    public Integer addT_User_Failed(User user, String password) throws SQLException;

    public Integer business(User user, String password) throws SQLException;
}
