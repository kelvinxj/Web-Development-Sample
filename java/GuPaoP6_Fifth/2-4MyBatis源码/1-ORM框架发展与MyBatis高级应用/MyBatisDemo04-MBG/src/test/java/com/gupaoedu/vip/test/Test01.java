package com.gupaoedu.vip.test;

import com.gupaoedu.vip.domain.User;
import com.gupaoedu.vip.mapper.UserMapperExt;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test01 {
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
     * 在MBG基础上扩展
     * @throws Exception
     */
    @Test
    public void test01() throws Exception{
        init();
        UserMapperExt mapper = session.getMapper(UserMapperExt.class);
        List<User> users = mapper.selectUserByName("zhangsan");
        for (User user : users) {
            System.out.println(user.getId() + "" + user.getUserName() + " " + user.getRealName());
        }
        session.close();
    }

    /**
     * 在MBG基础上扩展
     * @throws Exception
     */
    @Test
    public void test02() throws Exception{
        init();
        UserMapperExt mapper = session.getMapper(UserMapperExt.class);
        User user = mapper.selectByPrimaryKey(2);
         System.out.println(user.getId() + "" + user.getUserName() + " " + user.getRealName());
         session.close();
    }
}
