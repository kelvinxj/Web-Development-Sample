package com.gupaoedu.vip.pattern.builder.chain;

import org.apache.ibatis.mapping.CacheBuilder;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import java.util.Set;

/**
 * Created by Tom
 */
public class Test {
    public static void main(String[] args) {
        CourseBuilder builder = new CourseBuilder()
                    .addName("设计模式")
                    .addPPT("【PPT课件】")
                    .addVideo("【回放视频】")
                    .addNote("【课堂笔记】")
                    .addHomework("【课后作业】");

        System.out.println(builder.build());



        StringBuilder sb = new StringBuilder();
        sb.append("");


        CacheBuilder cacheBuilder = new CacheBuilder("");
        cacheBuilder.blocking(false);
//        cacheBuilder.

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
//        sqlSessionFactoryBuilder.


        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition();
//        beanDefinitionBuilder.
















    }
}
