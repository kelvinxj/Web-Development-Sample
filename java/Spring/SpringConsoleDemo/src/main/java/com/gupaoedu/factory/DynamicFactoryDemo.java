package com.gupaoedu.factory;

import com.gupaoedu.pojo.UserBean;

public class DynamicFactoryDemo {

    public UserBean getInstance(){
        return new UserBean();
    }
}
