package com.gupaoedu.vip.pattern.singleton.lazy;

/**
 * Created by Tom.
 */

/*
  ClassPath : LazyStaticInnerClassSingleton.class
              LazyStaticInnerClassSingleton$LazyHolder.class
   优点：写法优雅，利用了Java本身语法特点，性能高，避免了内存浪费,不能被反射破坏
   缺点：不优雅
 */
public class LazyStaticInnerClassSingleton {

    private LazyStaticInnerClassSingleton(){
        if(LazyHolder.INSTANCE != null){
            throw new RuntimeException("不允许非法访问");
        }
    }

    private static LazyStaticInnerClassSingleton getInstance(){
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder{
        private static final LazyStaticInnerClassSingleton INSTANCE = new LazyStaticInnerClassSingleton();
    }

}
