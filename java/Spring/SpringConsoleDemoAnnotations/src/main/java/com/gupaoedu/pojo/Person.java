package com.gupaoedu.pojo;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
//Person bean depends on user bean. "user" is the default name of User bean.
//In this case, the User bean can't depends on Person bean to prevent circular dependency
@DependsOn("user")
public class Person {
    public Person(){
        System.out.println("Person initialized...");
    }
}
