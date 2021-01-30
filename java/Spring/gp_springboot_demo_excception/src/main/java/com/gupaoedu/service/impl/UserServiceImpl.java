package com.gupaoedu.service.impl;

import com.gupaoedu.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public String query() {
        return "IUserService Unit test run.";
    }
}
