package com.gupaoedu.vip.pattern.flyweight.general;

// 具体享元角色
public class ConcreteFlyweight implements IFlyweight {
    private String intrinsicState;

    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }


    public void operation(String extrinsicState) {
        System.out.println("Object address: " + System.identityHashCode(this));
        System.out.println("IntrinsicState: " + this.intrinsicState);
        System.out.println("ExtrinsicState: " + extrinsicState);
    }
}