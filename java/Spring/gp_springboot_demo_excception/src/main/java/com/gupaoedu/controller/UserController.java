package com.gupaoedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping("/show")
    public String showInfo(Model model){
        model.addAttribute("msg","Hello, Thymeleaf...111");
        return "index";
    }

    @RequestMapping("/show1")
    public String showInfo1(){
        String message = null;
        message.length();
        return "success";
    }

    @RequestMapping("/show2")
    public String showInfo2(){
        int i = 100;
        int j = 0;
        System.out.println(i/j);
        return "success";
    }

    /**
     * This method only handles exception in this class.
     * @param e
     * @return
    @ExceptionHandler(value = {NullPointerException.class})
    public ModelAndView nullPointerExceptionHandler(Exception e){
        ModelAndView view = new ModelAndView();
        view.addObject("error",e.toString());
        view.setViewName("error1");
        return view;
    }
     */

    /**
     * This method only handles exception in this class.
     * @param e
     * @return
    @ExceptionHandler(value = {ArithmeticException.class})
    public ModelAndView arithmeticExceptionHandler(Exception e){
        ModelAndView view = new ModelAndView();
        view.addObject("error",e.toString());
        view.setViewName("error2");
        return view;
    }
     */
}
