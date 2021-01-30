package com.kelvin;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class dubboMain {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/user-provider.xml");
        context.start();

        System.in.read();
    }
}
