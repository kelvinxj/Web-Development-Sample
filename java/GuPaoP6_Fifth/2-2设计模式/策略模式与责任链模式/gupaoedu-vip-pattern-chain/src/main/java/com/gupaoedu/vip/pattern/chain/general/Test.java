package com.gupaoedu.vip.pattern.chain.general;

/**
 * Created by Tom.
 */
public class Test {
    public static void main(String[] args) {
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        handlerA.setNextHanlder(handlerB);
        handlerA.handleRequest("requestB");
    }
}
