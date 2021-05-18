package com.gupaoedu.vip.test;

import com.gupaoedu.vip.domain.Dept;
import com.gupaoedu.vip.domain.User;
import com.gupaoedu.vip.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 嵌套查询案例
 */
public class Test05RowBounds {

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
     * 逻辑分页查询
     * @throws Exception
     */
    @Test
    public void test01() throws Exception{
        init();
        UserMapper mapper = session.getMapper(UserMapper.class);
        RowBounds rowBounds = new RowBounds(1,3);
        List<User> users = mapper.queryUserList(rowBounds);
        for (User user : users) {
            System.out.println(user);
        }
    }


}
