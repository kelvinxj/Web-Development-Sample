package com.gupaoedu.vip.pattern.proxy.general;

/**
 * Created by Tom.
 */
public class RealSubject implements ISubject {

    public void request() {
        System.out.println("real service is called.");
    }

}
