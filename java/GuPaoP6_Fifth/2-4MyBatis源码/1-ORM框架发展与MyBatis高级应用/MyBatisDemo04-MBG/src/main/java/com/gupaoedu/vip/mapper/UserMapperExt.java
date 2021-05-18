package com.gupaoedu.vip.mapper;

import com.gupaoedu.vip.domain.User;

import java.util.List;

public interface UserMapperExt extends UserMapper {
    public List<User> selectUserByName(String userName);
}
