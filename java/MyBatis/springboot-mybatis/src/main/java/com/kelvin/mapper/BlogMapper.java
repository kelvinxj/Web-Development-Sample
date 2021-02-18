package com.kelvin.mapper;

import com.github.pagehelper.Page;
import com.kelvin.entity.Blog;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogMapper {
    @Select("select bid, name, author_id as authorId from blog")
    Page<Blog> getBlogListPage();

    @Select("select bid, name, author_id as authorId from blog")
    List<Blog> getBlogList();
}