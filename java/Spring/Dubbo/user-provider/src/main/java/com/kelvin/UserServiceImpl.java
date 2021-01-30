package com.kelvin;

public class UserServiceImpl implements IUserService{
    @Override
    public String getNameById(String id) {
        System.out.println("received service data: " + id);
        return "UserName";
    }
}
