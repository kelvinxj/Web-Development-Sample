package com.gupaoedu.vip.pattern.proxy.dynamicproxy.jdkproxy;


/**
 * Created by Tom.
 */
public class Test {
    public static void main(String[] args) {
        JdkMeipo jdkMeipo = new JdkMeipo();
        IPerson zhangsan = jdkMeipo.getInstance(new Zhangsan());
        zhangsan.findLove();


        IPerson zhaoliu = jdkMeipo.getInstance(new ZhaoLiu());
        zhaoliu.findLove();

    }
}
