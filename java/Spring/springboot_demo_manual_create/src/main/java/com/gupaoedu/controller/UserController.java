package com.gupaoedu.controller;

import com.gupaoedu.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Value("${user.userName}")
    private String username;

    @Value("${user.age}")
    private Integer age;

    @Value("${user.address}")
    private String address;

    @Autowired
    private User user;

    @GetMapping("/hello")
    public String hello(){
        return ("Hello, SpringBoot...maven, " + username + " " + age + " " + address);
    }

    @GetMapping("/hello1")
    public User query(){
        return user;
    }

    @GetMapping("/hello2")
    public User1 queryUser1(){
        User1 user1 = new User1(43, "Excellent");
        return user1;
    }
    
    public class User1{
        private Integer useId;

        private String userScore;

        public Integer getUseId() {
            return useId;
        }

        public void setUseId(Integer useId) {
            this.useId = useId;
        }

        public String getUserScore() {
            return userScore;
        }

        public void setUserScore(String userScore) {
            this.userScore = userScore;
        }

        public User1(Integer useId, String userScore) {
            this.useId = useId;
            this.userScore = userScore;
        }
    }
}
