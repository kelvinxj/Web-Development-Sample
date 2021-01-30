package com.gupaoedu.controller;

import com.gupaoedu.pojo.User;
import com.gupaoedu.service.IUserService;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    /**
     * Ignore favicon.ico error
     */
    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }

    @Autowired
    private IUserService service;

//    @RequestMapping("/user/query")
//    public List<User> query(){
//        return service.query(null);
//    }

    /**
     * block all request.
     * return view name.
     * e.g.:
     * http://localhost:8080/index.html will return "index.html" as its view name.
     * @param //page
     * @return
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){

        return page;
    }

    @RequestMapping("/user/first")
    public String first(){
        return "user";
    }

    @RequestMapping("/user/query")
    public String query(Model model){
        model.addAttribute("list",service.query(null));
        return "user";
    }

    @RequestMapping("/user/save")
    public String addUser(User user){
        service.addUser(user);
        return "redirect:/user/query";
    }

    @RequestMapping("/user/updateInfo")
    public String updateInfo(Integer id, Model model){
        User user = service.queryById(id);
        model.addAttribute("user",user);
        return "updateUser";
    }

    @RequestMapping("/user/deleteUser")
    public String deleteUser(Integer id){
        service.deleteUser(id);
        return "redirect:/user/query";
    }

    @RequestMapping("/user/update")
    public String updateUser(User user){
        service.updateUser(user);
        return "redirect:/user/query";
    }
}
