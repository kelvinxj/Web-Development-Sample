package com.gupaoedu.vip.cache;

import com.gupaoedu.vip.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 缓存的测试
 *
 */
public class Test01 {

    @Test
    public void test01() throws  Exception{
        // 1.获取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.加载解析配置文件并获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3.根据SqlSessionFactory对象获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 4.通过SqlSession中提供的 API方法来操作数据库
        List<User> list = sqlSession.selectList("com.gupaoedu.vip.mapper.UserMapper.selectUserList");
        System.out.println(list.size());
        // 一级缓存测试
        System.out.println("---------");
        list = sqlSession.selectList("com.gupaoedu.vip.mapper.UserMapper.selectUserList");
        System.out.println(list.size());
        // 5.关闭会话
        sqlSession.close();
        System.out.println("**********");
        sqlSession = factory.openSession();
        list = sqlSession.selectList("com.gupaoedu.vip.mapper.UserMapper.selectUserList");
        System.out.println(list.size());
        sqlSession.close();
    }

    @Test
    public void test2() throws  Exception{
        // 1.获取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.加载解析配置文件并获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3.根据SqlSessionFactory对象获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 4.通过SqlSession中提供的 API方法来操作数据库
        List<User> list = sqlSession.selectList("com.gupaoedu.vip.mapper.UserMapper.selectUserList");
        System.out.println(list.size());
        sqlSession.close();
        sqlSession = factory.openSession();
        // 一级缓存测试
        System.out.println("---------");
        list = sqlSession.selectList("com.gupaoedu.vip.mapper.UserMapper.selectUserList");
        System.out.println(list.size());
        // 5.关闭会话
        sqlSession.close();
    }
}
