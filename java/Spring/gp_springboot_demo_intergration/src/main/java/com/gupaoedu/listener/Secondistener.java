package com.gupaoedu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


public class Secondistener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce){
        System.out.println("Second listener initialized...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Second listener destroyed...");
    }
}
