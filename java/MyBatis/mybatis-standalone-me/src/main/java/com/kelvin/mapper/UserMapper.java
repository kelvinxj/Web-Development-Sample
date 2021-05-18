package com.kelvin.mapper;

import com.kelvin.entity.User;

import java.util.List;

public interface UserMapper {

    public List<User> selectAllUsers();

    public User selectUserById(Integer userId);

    public void updateUserById(User user);
}
