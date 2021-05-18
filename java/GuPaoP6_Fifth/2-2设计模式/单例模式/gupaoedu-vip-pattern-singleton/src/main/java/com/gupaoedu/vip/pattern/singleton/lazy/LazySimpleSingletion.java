package com.gupaoedu.vip.pattern.singleton.lazy;

/**
 * Created by Tom.
 */

/**
 * 优点：节省了内存,线程安全
 * 缺点：性能低
 */
public class LazySimpleSingletion {
    private static LazySimpleSingletion instance;
    private LazySimpleSingletion(){}

    public synchronized static LazySimpleSingletion getInstance(){
        if(instance == null){
            instance = new LazySimpleSingletion();
        }
        return instance;
    }
}
