package com.gupaoedu.vip.pattern.prototype.singleton;


import lombok.Data;

import java.util.List;

/**
 * Created by Tom.
 */
@Data
public class ConcretePrototype implements Cloneable {

    private int age;
    private String name;
    private List<String> hobbies;

    private static  ConcretePrototype instance = new ConcretePrototype();

    private ConcretePrototype(){}

    public static ConcretePrototype getInstance(){
        return instance;
    }

    @Override
    public ConcretePrototype clone() {
       return instance;
    }


    @Override
    public String toString() {
        return "ConcretePrototype{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", hobbies=" + hobbies +
                '}';
    }
}
