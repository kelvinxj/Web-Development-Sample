package com.kelvin;

import jdk.nashorn.internal.ir.Block;

import java.util.concurrent.TimeUnit;

public class ThreadStatusDemo {
    public static void main(String[] args) {
        //time waiting demo
        new Thread(()->{
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ,"Time_Waiting_Demo"
        ).start();

        //waiting demo
        new Thread(()->{
            while(true){
                synchronized (ThreadStatusDemo.class){
                    try {
                        //cause current thread(thread waiting demo) to wait.
                        ThreadStatusDemo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Waiting demo").start();

        new Thread(new BlockDemo(),"block demo1").start();
        new Thread(new BlockDemo(), "block demo2").start();
    }

    static class BlockDemo extends Thread{
        @Override
        public void run() {
            while(true){
                synchronized (BlockDemo.class){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
