package com.gupaoedu.vip.pattern.prototype.general;

/**
 * Created by Tom.
 */
public class ConcretePrototype implements IPrototype {

    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ConcretePrototype clone() {
        ConcretePrototype concretePrototype = new ConcretePrototype();
        concretePrototype.setAge(this.age);
        concretePrototype.setName(this.name);
        return concretePrototype;
    }

    @Override
    public String toString() {
        return "ConcretePrototype{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
