package com.kelvin;

import static org.junit.Assert.assertTrue;

import com.kelvin.entity.Blog;
import com.kelvin.mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Unit test for simple App.
 */
public class MyBatisTest
{
    @Test
    public void testSelectBlogById() throws IOException {
        String config = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(config);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        System.out.println("first query");
        Blog blog = blogMapper.selectBlogById(1);
        System.out.println(blog);

        //same query created by same sqlSession will use first level cache.
        //second query will not display sql.
        System.out.println("second query");
        blog = blogMapper.selectBlogById(1);
        System.out.println(blog);
    }

    @Test
    public void testSelelctBlogByIdSecondLevelCache() throws IOException {
        String config = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(config);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        System.out.println("first query");
        Blog blog = blogMapper.selectBlogById(1);
        //commit to enable second level cache
        sqlSession.commit();
        System.out.println(blog);

        System.out.println("second query");
        SqlSession sqlsession2 = sqlSessionFactory.openSession();
        BlogMapper blogMapper2 = sqlsession2.getMapper(BlogMapper.class);
        blog = blogMapper2.selectBlogById(1);
        System.out.println(blog);
    }

    @Test
    public void testSelectByTwoParameter() throws IOException {
        String config = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(config);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectBlogByAuthorIdAndBlogName(1001,"test");
        System.out.println(blog);
    }
}
