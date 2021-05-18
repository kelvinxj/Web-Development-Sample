package com.gupaoedu.vip.test;

import com.gupaoedu.vip.domain.User;
import com.gupaoedu.vip.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test03Batch {

    public SqlSession session;


    public void init() throws IOException {
        // 1.获取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.加载解析配置文件并获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3.根据SqlSessionFactory对象获取SqlSession对象
        session = factory.openSession();
    }

    /**
     * 循环插入10000
     */
    @Test
    public void test1() throws Exception{
        init();
        long start = System.currentTimeMillis();
        UserMapper mapper = session.getMapper(UserMapper.class);
        int count = 12000;
        for (int i=2000; i< count; i++) {
            User user = new User();
            user.setUserName("a"+i);
            mapper.insertUser(user);
         }
         session.commit();
         session.close();
         long end = System.currentTimeMillis();
         System.out.println("循环批量插入"+count+"条，耗时：" + (end -start )+"毫秒");
    }

    /**
     * 批量插入
     */
    @Test
    public void test2() throws Exception{
        init();
        long start = System.currentTimeMillis();
        UserMapper mapper = session.getMapper(UserMapper.class);
        int count = 12000;
        List<User> list = new ArrayList<>();
        for (int i=2000; i< count; i++) {
            User user = new User();
            user.setUserName("a"+i);
            list.add(user);
        }
        mapper.insertUserList(list);
        session.commit();
        session.close();
        long end = System.currentTimeMillis();
        System.out.println("循环批量插入"+count+"条，耗时：" + (end -start )+"毫秒");
    }

    /**
     * 批量更新
     */
    @Test
    public void test3() throws Exception{
        init();
        long start = System.currentTimeMillis();
        UserMapper mapper = session.getMapper(UserMapper.class);
        int count = 12000;
        List<User> list = new ArrayList<>();
        for (int i=2000; i< count; i++) {
            User user = new User();
            user.setId(i);
            user.setUserName("a"+i);
            list.add(user);
        }
        mapper.updateUserList(list);
        session.commit();
        session.close();
        long end = System.currentTimeMillis();
        System.out.println("批量更新"+count+"条，耗时：" + (end -start )+"毫秒");
    }

    @Test
    public void testJdbcBatch() throws IOException {
         Connection conn = null;
         PreparedStatement ps = null;

         try {
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatisdb?characterEncoding=utf-8&serverTimezone=UTC", "root", "123456");
           ps = conn.prepareStatement("INSERT into t_user(user_name,real_name) values ( ?, ?)");

           for (int i = 1000; i < 101000; i++) {
               ps.setString(1,"a"+i);
               ps.setString(2,"b"+i);
               ps.addBatch();
           }
           // 执行批处理
           ps.executeBatch();
           ps.close();
           conn.close();
         } catch (SQLException se) {
           se.printStackTrace();
         } catch (Exception e) {
           e.printStackTrace();
         } finally {
           try {
             if (ps != null) ps.close();
           } catch (SQLException se2) {
           }
           try {
             if (conn != null) conn.close();
           } catch (SQLException se) {
             se.printStackTrace();
           }
         }
    }
}
