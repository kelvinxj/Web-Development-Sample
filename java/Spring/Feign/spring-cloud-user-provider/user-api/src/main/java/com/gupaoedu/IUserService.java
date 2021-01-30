package com.gupaoedu;

import org.springframework.web.bind.annotation.PostMapping;

public interface IUserService {

    @PostMapping("/user")
    public String insertUser(User user);
}
