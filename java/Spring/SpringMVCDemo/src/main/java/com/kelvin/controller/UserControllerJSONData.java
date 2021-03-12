package com.kelvin.controller;

import com.kelvin.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/json")
public class UserControllerJSONData {

    /**
     * enable return json string, add dependency:
     *     <dependency>
     *       <groupId>com.fasterxml.jackson.core</groupId>
     *       <artifactId>jackson-core</artifactId>
     *       <version>2.11.0</version>
     *     </dependency>
     *     <dependency>
     *       <groupId>com.fasterxml.jackson.core</groupId>
     *       <artifactId>jackson-databind</artifactId>
     *       <version>2.11.0</version>
     *     </dependency>
     *
     *    and also enable annotation driven:
     *      <mvc:annotation-driven/>
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public List<User> query(){
        List<User> list = new LinkedList<User>();
        list.add(new User("user1",19));
        list.add(new User("user2",22));
        return list;
    }

    /**
     * post json data to this url, property name should be same as pojo property name(case sensitive)
     * http://localhost:8080/SpringMVCDemo/json/save
     * {"name":"sam","age":"12","favourites":["abc","aaa"],"loves":["love1","love2"]}
     *
     * or access:
     * http://localhost:8080/SpringMVCDemo/user.jsp
     * click button to send post request
     *
     * note: the pojo type User should have default constructor.
     * @param user
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public String add(@RequestBody User user){
        System.out.println("save..." + user);
        return user.toString();
    }

}
