package com.example.demo;

import com.gupaoedu.IUserService;
import com.gupaoedu.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServiceImpl implements IUserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public String insertUser(@RequestBody User user) {
        logger.info("received user " + user);
        logger.info("user name:" + user.getName());
        return "SAVE SUCCESS. saved user name:" + user.getName();
    }
}
