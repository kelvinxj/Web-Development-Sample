package com.kelvin;

import com.kelvin.entity.User;
import com.kelvin.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class UserTableTest {
    @Test
    public void testSelectAllUser() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        //first query
        System.out.println("first query");
        List<User> list = userMapper.selectAllUsers();
        System.out.println(list);

        //second query(will use local cache)
//        System.out.println("second query");
//        list = userMapper.selectAllUsers();
//        System.out.println(list);
        session.close();
    }

    @Test
    public void testSelectUserById() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectUserById(1);
        System.out.println(user);
        session.close();
    }

    @Test
    public void testUpdateUser() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();

        UserMapper userMapper = session.getMapper(UserMapper.class);

        Random r = new Random();
        User u = new User();
        u.setId(2);
        u.setName("user2" + r.nextInt());
        u.setAge(22);
        u.setFavourites(new LinkedList<String>());
        u.getFavourites().add("art");
        u.getFavourites().add("sport");

        userMapper.updateUserById(u);
        session.commit();
        session.close();
    }
}
