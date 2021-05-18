package com.gupaoedu.vip.pattern.flyweight.general;

/**
 * Created by Tom.
 */
public class Test {
    public static void main(String[] args) {
        IFlyweight flyweight1 = FlyweightFactory.getFlyweight("aa");
        IFlyweight flyweight2 = FlyweightFactory.getFlyweight("bb");
        flyweight1.operation("a");
        flyweight2.operation("b");
    }
}
