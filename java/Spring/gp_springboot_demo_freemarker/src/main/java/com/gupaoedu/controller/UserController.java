package com.gupaoedu.controller;

import com.gupaoedu.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping("/showUser")
    public String showUser(Model model){
        List<User> list = new LinkedList<User>();
        list.add(new User(1,"first user",18));
        list.add(new User(2,"Cheery",28));
        list.add(new User(3,"Kazakowski",11));
        model.addAttribute("list", list);

        //"user" is template file name.
        return "user";
    }
}
