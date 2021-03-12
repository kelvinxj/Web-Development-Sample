package com.kelvin.pojo;

import java.util.Arrays;
import java.util.List;

public class User {

    Integer id;

    String name;

    Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private List favourites;

    public List getFavourites() {
        return favourites;
    }

    public void setFavourites(List favourites) {
        this.favourites = favourites;
    }

    public String[] getLoves() {
        return loves;
    }

    public void setLoves(String[] loves) {
        this.loves = loves;
    }

    private String[] loves;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", favourites=" + favourites +
                ", loves=" + Arrays.toString(loves) +
                '}';
    }

    public User(){

    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
