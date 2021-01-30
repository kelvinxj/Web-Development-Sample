package com.gupaoedu.dao.impl;

import com.gupaoedu.dao.IUserDao;
import com.gupaoedu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Add new user and return generated user id
     * @param user
     * @return
     */
    @Override
    public Integer addUser(User user) {
        String sql = "insert into user(name, age) values(?,?)";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
                ps.setString(1,user.getName());
                ps.setInt(2,user.getAge());
                return ps;
            }
        }
        ;

        KeyHolder holder = new GeneratedKeyHolder();
        int count = jdbcTemplate.update(psc, holder);
        return holder.getKey().intValue();

        //return jdbcTemplate.update(sql, user.getName(),user.getAge());
        //System.out.println("user added");
    }

    @Override
    public Integer addT_User(User user, String password){
        String sql = "insert into t_user(name, password) values(?,?)";
        return jdbcTemplate.update(sql, user.getName(),password);
    }

    @Override
    public Integer addT_User_Failed(User user, String password) {
        String sql = "insert into t_user(name, password1) values(?,?)";
        return jdbcTemplate.update(sql, user.getName(),password);
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
