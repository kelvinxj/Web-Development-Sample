package com.gupaoedu.vip.pattern.flyweight.jdk.integer;

/**
 * Created by Tom.
 */
public class IntegerTest {
    public static void main(String[] args) {

        Integer a = Integer.valueOf(100);
        Integer b = 100;

        Integer c = Integer.valueOf(1000);
        Integer d = 1000;

        System.out.println("a==b:" + (a==b));
        System.out.println("c==d:" + (c==d));
    }
}
