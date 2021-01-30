package com.gupaoedu.pojo;

public class Cat {

    private String nick;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private String color;

    @Override
    public String toString() {
        return "Cat{" +
                "nick='" + nick + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
