package com.gupaoedu.pojo;

import java.util.List;

public class User {

    Integer id;

    String userName;

    Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + userName + '\'' +
                ", age=" + age +
                '}';
    }

    public User(String name, Integer age) {
        this.userName = name;
        this.age = age;
    }

    public User(){

    }
}
