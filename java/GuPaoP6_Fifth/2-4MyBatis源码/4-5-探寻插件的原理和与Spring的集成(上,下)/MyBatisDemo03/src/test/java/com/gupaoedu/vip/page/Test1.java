package com.gupaoedu.vip.page;

import com.github.pagehelper.PageHelper;
import com.gupaoedu.vip.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 分页查询
 */
public class Test1 {

    @Test
    public void test1() throws  Exception{
        // 1.获取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.加载解析配置文件并获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3.根据SqlSessionFactory对象获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 4. 分页查询设置
        PageHelper.startPage(1,5);
        // 通过SqlSession中提供的 API方法来操作数据库
        List<User> list = sqlSession.selectList("com.gupaoedu.vip.mapper.UserMapper.selectUserList");
        System.out.println(list.size());
        // 5.关闭会话
        sqlSession.close();
    }
}
