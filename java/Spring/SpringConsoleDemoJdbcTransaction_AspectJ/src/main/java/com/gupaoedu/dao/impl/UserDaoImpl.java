package com.gupaoedu.dao.impl;

import com.gupaoedu.dao.IUserDao;
import com.gupaoedu.pojo.User;
import com.gupaoedu.utils.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements IUserDao {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private String sql = "";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer addUser(User user) throws SQLException {
//        String sql = "insert into user(name, age) values(?,?)";
//        return jdbcTemplate.update(sql, user.getName(),user.getAge());
        //System.out.println("user added");

        int count = 0;
        sql = "insert into user(name, age) values(?,?)";
        ps = DBUtil.getConnection().prepareStatement(sql);
        ps.setString(1,user.getName());
        ps.setInt(2,user.getAge());
        count = ps.executeUpdate();
        return count;
    }

    @Override
    public Integer addT_User(User user, String password){
        String sql = "insert into t_user(name, password) values(?,?)";
        return jdbcTemplate.update(sql, user.getName(),password);
    }

    @Override
    public Integer addT_User_Failed(User user, String password) throws SQLException {
//        String sql = "insert into t_user(name, password1) values(?,?)";
//        return jdbcTemplate.update(sql, user.getName(),password);

        int count = 0;
        sql = "insert into t_user(name, password1) values(?,?)";
        ps = DBUtil.getConnection().prepareStatement(sql);
        ps.setString(1,user.getName());
        ps.setString(2, password);
        count = ps.executeUpdate();
        return count;
    }

    @Override
    public List<User> query() {
//        System.out.println("user list were queried");
        String sql = "select * from user";
        return jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                return user;
            }
        });
    }
}
