package com.gupaoeud.vip.mebatis.v2.mapper;


import com.gupaoeud.vip.mebatis.v2.annotation.Entity;
import com.gupaoeud.vip.mebatis.v2.annotation.Select;
import com.gupaoeud.vip.mebatis.v2.domain.User;
@Entity(User.class)
public interface UserMapper {

    @Select("select * from t_user where id = ?")
    public User selectOne(Integer id);
}
