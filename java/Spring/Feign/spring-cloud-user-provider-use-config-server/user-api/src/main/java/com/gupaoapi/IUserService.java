package com.gupaoapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface IUserService {

    @PostMapping("/user")
    public String insertUser(User user);

    @GetMapping("/query")
    public User query();
}
