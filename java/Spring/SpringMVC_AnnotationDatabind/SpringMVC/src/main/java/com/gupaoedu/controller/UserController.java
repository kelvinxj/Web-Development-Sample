package com.gupaoedu.controller;

import com.gupaoedu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
//this RequestMapping can be ignored.
@RequestMapping("/user")
//means to add variable "msg" to session
@SessionAttributes({"msg"})
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
    /**
     * data bind by ModelAndView
     */
    public ModelAndView query(String username){
        System.out.println("name=" + username);
        ModelAndView mm = new ModelAndView();
        mm.setViewName("/index.jsp");
        mm.addObject("msg","msg 你好:" + username);
        return mm;
    }

    @RequestMapping("/save")
    /**
     * data bind by map object.
     * map.put(nameInJspFile,Object);
     */
    public String add(Map<String, Object> map){
        System.out.println("save...");
        map.put("msg","map...message");
        return "/index.jsp";
    }

    @RequestMapping("/noresponse")
    @ResponseBody
    public void noResponseFunc(){
        System.out.println("no  response message to client");
    }

    @RequestMapping("/delete")
    /**
     * send parameters as array:
     * http://localhost:8080/SpringMVC/user/delete?loves=basketball&loves=football
     */
    public String delete(Model model){
        System.out.println("delete...");
        model.addAttribute("msg","model...msg");
        return "/index.jsp";
    }

    @RequestMapping("/update")
    public String update(ModelMap mm){
        System.out.println("update...");
        mm.put("msg","mm...msg");
        return "/index.jsp";
    }

    @RequestMapping("/func1")
    /**
     * get HttpServletRequest and HttpServletResponse object
     */
    public void func1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
