package com.gupaoedu.controller;

import com.gupaoedu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
//this RequestMapping can be ignored.
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
    /**
     * get parameter of primitive type, need to define them in parameters of method
     * request:
     * (birth can be null) http://localhost:8080/SpringMVC/user/query?id=1&name=lilsi
     * http://localhost:8080/SpringMVC/user/query?id=1&name=lilsi&birth=2021-01-31%2011:15:27
     *
     */
    public String query(int id, String name, Date birth){
        System.out.println("id=" + id + ";name="+ name + ";" + birth);
        return "/index.jsp";
    }

    @RequestMapping("/save")
    /**
     * send a user object:
     * http://localhost:8080/SpringMVC/user/save?id=1&name=Lisi&age=18
     * parameter name "age" and property "age" should be same letters(case could be different)
     *
     * send collection:
     *http://localhost:8080/SpringMVC/user/save?loves=basketball&loves=football&favourites=f1&favourites=f2
     */
    public String add(User user){
        System.out.println("save..." + user);
        System.out.println(user.getFavourites());
        if(user.getLoves() != null)
            for(String l: user.getLoves()){
                System.out.println(l);
            }
        return "/index.jsp";
    }

    @RequestMapping("/noresponse")
    @ResponseBody
    public void noResponseFunc(){
        System.out.println("no  response message to client");
    }

    @RequestMapping("/modelAndView")
    public ModelAndView returnModelAndView(){
        System.out.println("delete...");
        ModelAndView mm = new ModelAndView();
        mm.setViewName("/index.jsp");
        return mm;
    }

    @RequestMapping("/delete")
    /**
     * send parameters as array:
     * http://localhost:8080/SpringMVC/user/delete?loves=basketball&loves=football
     */
    public String delete(String[] loves){
        if(loves != null)
            for(String l: loves){
                System.out.println(l);
            }
        System.out.println("delete...");
        return "/index.jsp";
    }

    @RequestMapping("/update")
    public String update(List<String> loves){
        if(loves != null)
            for(String l: loves){
                System.out.println(l);
            }
        System.out.println("update...");
        return "redirect:/user/query";
    }

    @RequestMapping("/func1")
    /**
     * get HttpServletRequest and HttpServletResponse object
     */
    public void func1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
