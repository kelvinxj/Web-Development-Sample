package com.gupaoedu.vip.dao;

import com.gupaoedu.vip.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User,Integer> {
}
