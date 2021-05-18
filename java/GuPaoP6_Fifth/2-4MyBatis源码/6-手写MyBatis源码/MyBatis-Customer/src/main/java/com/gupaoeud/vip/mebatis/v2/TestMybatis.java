package com.gupaoeud.vip.mebatis.v2;


import com.gupaoeud.vip.mebatis.v2.domain.User;
import com.gupaoeud.vip.mebatis.v2.mapper.UserMapper;
import com.gupaoeud.vip.mebatis.v2.session.DefaultSqlSession;
import com.gupaoeud.vip.mebatis.v2.session.SqlSessionFactory;

/**
 * @Author: qingshan
 */
public class TestMybatis {

    public static void main(String[] args) {
        SqlSessionFactory factory = new SqlSessionFactory();
        DefaultSqlSession sqlSession = factory.build().openSqlSession();
        // 获取包含了h MapperProxy代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectOne(1);

        System.out.println("第一次查询: " + user);
        System.out.println();
        user = mapper.selectOne(1);
        System.out.println("第二次查询: " + user);
    }
}
