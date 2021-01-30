package com.gupaoedu;

import com.gupaoedu.controller.UserController;
import com.gupaoedu.dao.IUserDao;
import com.gupaoedu.dao.impl.UserDaoImpl;
import com.gupaoedu.service.IUserService;
import com.gupaoedu.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    public IUserDao getUserDao(){
        return new UserDaoImpl();
    }

    @Bean
    /**
     * when this method was called, it will search userDao from project, by type IUserDao
     */
    public IUserService getUserService(IUserDao userDao){
        IUserService userService = new UserServiceImpl();
        ((UserServiceImpl)userService).setDao(userDao);
        return userService;
    }

    @Bean
    public UserController getController(IUserService userService){
        UserController userController = new UserController();
        userController.setUserService(userService);
        return userController;
    }
}
