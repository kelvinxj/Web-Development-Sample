package com.kelvin.controller;

import com.kelvin.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
//means when set value to attribute "msg",
//also set value to session variable "msg"
@SessionAttributes({"msg"})
public class MyController {

    /**
     * return view name: jsp file name.
     * @return
     */
    @RequestMapping("/test")
    public String testRequest(){
        return "index.jsp";
    }

    /**
     * return view name, without .jsp suffix
     * the same as previous one. to enable this one, should define bean:
     *    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     *         <property name="prefix" value="/"/>
     *         <property name="suffix" value=".jsp"/>
     *     </bean>
     * @return
     */
    @RequestMapping("/test1")
    public String testRequest1(){
        return "index";
    }

    /**
     * return view name: jsp file name.
     * set attribute to model.
     * need to configure jsp:
     *  isELIgnored="false"
     * @return
     */
    @RequestMapping("/testvalue")
    public ModelAndView testRequestSetModelValue(String name){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.jsp");
        modelAndView.addObject("msg","你好, " + name);
        return modelAndView;
    }

    @RequestMapping("/testvaluemap")
    /**
     * data bind by map object.
     * set value to map object means to set value to attribute in model "index.jsp"
     * map.put(nameInJspFile,Object);
     * url:
     * http://localhost:8080/SpringMVCDemo/testvaluemap?id=1
     */
    public String testvaluemap(Map<String, Object> map, String id){
        System.out.println("save...");
        map.put("msg","map...message, id=" + id);
        return "/index.jsp";
    }

    @RequestMapping("/testvaluemodel")
    /**
     * data bind by model
     * set value to model means to set attribute to view "index.jsp"
     * send parameters as array:
     */
    public String testvaluemodel(Model model, String id){
        System.out.println("delete...");
        model.addAttribute("msg","model...msg, id=" + id);
        return "/index.jsp";
    }

    /**
     * Get parameters from request.
     * for non primitive data, need to define converter bean
     * request:
     * http://localhost:8080/SpringMVCDemo/check?id=1&dateOfBirth=2021-01-31
     * or
     * http://localhost:8080/SpringMVCDemo/check?id=1&dateOfBirth=2021-01-31%2021:22:59
     * @param myid
     * @return
     */
    @RequestMapping("/check")
    public String checkParamInRequest(@RequestParam("id") String myid, Date dateOfBirth){
        System.out.println("id: " + myid);
        System.out.println("Date of birth is: " + dateOfBirth);
        return "index.jsp";
    }

    /**
     * send a user object:
     * http://localhost:8080/SpringMVCDemo/save?id=1&name=Lisi&age=18
     * parameter name "age" and property "age" should be same letters(case could be different)
     * send collection:
     * http://localhost:8080/SpringMVCDemo/save?id=1&name=Lisi&age=18&loves=basketball&loves=football&favourites=f1&favourites=f2
     * @param user
     * @return
     */
    @RequestMapping("/save")
    public String saveUser(User user){
        System.out.println("saved user: " + user);
        System.out.println("user loves: " + user.getLoves());
        List<String> favs = user.getFavourites();
        if(favs != null)
            for(String f: favs){
                System.out.println("favourites: " + f);
            }
        return "index.jsp";
    }

    /**
     * get parameters from path
     * @param pId
     * @return
     */
    @RequestMapping("/getperson/{id}")
    public String getPersonById(@PathVariable("id") Integer pId){
        System.out.println("person id: " + pId);
        //should return "/index.jsp". because return "index.jsp" means "index.jsp" in current level:
        //http://localhost:8080/springmvcDemo/getperson/index.jsp
        return "/index1.html";
    }

    /**
     * access html in web-inf
     * @return
     */
    @RequestMapping("/index")
    public String func1(){
        return "/WEB-INF/index2.html";
    }

    /**
     * send parameters as array:
     * http://localhost:8080/SpringMVC/user/delete?loves=basketball&loves=football
     */
    @RequestMapping("/delete")
    public String delete(String[] loves){
        if(loves != null)
            for(String l: loves){
                System.out.println(l);
            }
        System.out.println("delete...");
        return "index.jsp";
    }

    /**
     * Use HttpServletRequest and HttpServletResponse object
     */
    @RequestMapping("/func1")
    public void func1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * return no response to client
     */
    @RequestMapping("/noresponse")
    @ResponseBody
    public void noResponseFunc(){
        System.out.println("no  response message to client");
    }
}
