package com.kelvin.controller;

import com.kelvin.bean.User;
import com.kelvin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/first")
    public String first(Model model){
        List<User> list = iUserService.queryList();
        model.addAttribute("userList",list);
        return "index";
    }

    @RequestMapping("/query")
    @ResponseBody
    public List<User> query(){
        List<User> list = iUserService.queryList();
        return list;
    }
}
