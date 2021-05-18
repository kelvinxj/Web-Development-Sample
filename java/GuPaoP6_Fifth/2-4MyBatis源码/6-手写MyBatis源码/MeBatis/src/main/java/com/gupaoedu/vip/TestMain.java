package com.gupaoedu.vip;

import com.gupaoedu.vip.domain.User;
import com.gupaoedu.vip.mapper.UserMapper;

public class TestMain {
    public static void main(String[] args) {
        GpSqlSession sqlSession = new GpSqlSession();
        // User user = sqlSession.selectOne("com.gupaoedu.vip.domain.User.selectOne",1);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(mapper.selectOne(1));
    }
}
