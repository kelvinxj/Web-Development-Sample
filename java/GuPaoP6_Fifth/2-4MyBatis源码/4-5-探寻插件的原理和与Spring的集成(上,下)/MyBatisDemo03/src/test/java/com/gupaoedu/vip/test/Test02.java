package com.gupaoedu.vip.test;

import com.gupaoedu.vip.domain.User;
import com.gupaoedu.vip.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * MyBatis的基本使用
 */
public class Test02 {

    /**
     * MyBatis API 的使用  - TypeHandler 的使用
     * @throws Exception
     */

    @Test
    public void test1() throws Exception{
        // 1.获取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.加载解析配置文件并获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3.根据SqlSessionFactory对象获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 4.通过SqlSession中提供的 API方法来操作数据库
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectUserById(1);
        System.out.println(user);
        // 5.关闭会话
        sqlSession.close();
    }

}
