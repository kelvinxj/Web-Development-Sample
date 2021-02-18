package com.kelvin.test.objectfactory;

import com.kelvin.test.domain.Blog;

/**
 * @Author: qingshan
 */
public class ObjectFactoryTest {
    public static void main(String[] args) {
        MyObjectFactory factory = new MyObjectFactory();
        Blog myBlog = (Blog) factory.create(Blog.class);
        System.out.println(myBlog);
    }
}
