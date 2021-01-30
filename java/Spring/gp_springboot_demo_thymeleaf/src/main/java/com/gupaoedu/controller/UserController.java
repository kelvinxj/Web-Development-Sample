package com.gupaoedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping("/show")
    public String showInfo(Model model){
        model.addAttribute("msg","Hello, Thymeleaf...111");
        return "index";
    }
}
