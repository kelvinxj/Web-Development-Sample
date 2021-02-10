package com.example.demo;

import com.gupaoapi.IUserService;
import com.gupaoapi.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class UserServiceImpl implements IUserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public String insertUser(@RequestBody User user) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("received user " + user);
        logger.info("user name:" + user.getName());
        return "SAVE SUCCESS. saved user name:" + user.getName();
    }

    @Override
    public User query() {
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        User user = new User();
        user.setName("New user");
        return user;
    }
}
