package com.kelvin.kelvinTestProjectMaven.threadTest.threadLocal;

import javax.sound.sampled.Line;

public class InfoObject {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InfoObject(int id, String name){
        this.id = id;
        this.name = name;
    }
}
