package com.kelvin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kelvin.entity.Blog;
import com.kelvin.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogController {
    @Autowired
    BlogMapper blogMapper;

    /**
     * Use paging
     * @param pageNum
     * @param pageSize
     * @return
     */
    // http://localhost:8080/getBlogListPage?pageNum=1&pageSize=2
    @RequestMapping("/getBlogListPage")
    public Page<Blog> getUserList(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        Page<Blog> userList= blogMapper.getBlogListPage();
        return userList;
    }

    /**
     * do not use paging
     * @return
     */
    //http://localhost:8080/getBlogList
    @RequestMapping("/getBlogList")
    public List<Blog> getUserList(){
        List<Blog> userList= blogMapper.getBlogList();
        return userList;
    }

}
