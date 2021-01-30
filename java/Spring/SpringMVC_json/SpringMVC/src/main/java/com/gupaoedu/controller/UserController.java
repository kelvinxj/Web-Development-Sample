package com.gupaoedu.controller;

import com.gupaoedu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController{

    @RequestMapping("/queryByViewName")
    //this method requires:
    //<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    //  <property name="suffix" value=".jsp"/>
    //  <property name="prefix" value="/"/>
    //</bean>
    public String queryByViewName(){
        System.out.println("query123...");
        //return view name: /index.jsp
        return "index";
    }

    @RequestMapping("/query")
    @ResponseBody
    public List<User> query(){
        System.out.println("query...");
        return Arrays.asList(new User("zhangsan1",18)
        ,new User("zhangSan2",19)
        ,new User("zhangsan3",12));
    }

    @RequestMapping("/save")
    @ResponseBody
    public String add(@RequestBody User user){
        System.out.println("save..." + user);
        return user.toString();
    }
//
//    @RequestMapping("/noresponse")
//    @ResponseBody
//    public void noResponseFunc(){
//        System.out.println("no  response message to client");
//    }
//
//    @DeleteMapping("/user")
//    /**
//     * send parameters as array:
//     * http://localhost:8080/SpringMVC/user/delete?loves=basketball&loves=football
//     */
//    public String delete(Model model){
//        System.out.println("delete...");
//        model.addAttribute("msg","model...msg");
//        return "/index.jsp";
//    }
//
//    @PutMapping("/user")
//    public String update(ModelMap mm){
//        System.out.println("update...");
//        mm.put("msg","mm...msg");
//        return "/index.jsp";
//    }
//
//    @RequestMapping("/func1")
//    /**
//     * get HttpServletRequest and HttpServletResponse object
//     */
//    public void func1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
//    }
}
