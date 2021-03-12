package com.kelvin.mapper;

import com.kelvin.entity.Blog;
import org.apache.ibatis.annotations.Param;

public interface BlogMapper {
    public Blog selectBlogById(Integer blogId);

    public Blog selectBlogByAuthorIdAndBlogName(@Param("authorId") Integer author_id, @Param("name") String content);
}
