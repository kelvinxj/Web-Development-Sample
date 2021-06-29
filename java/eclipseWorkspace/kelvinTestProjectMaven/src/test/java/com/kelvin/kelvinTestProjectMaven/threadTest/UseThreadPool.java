package com.kelvin.kelvinTestProjectMaven.threadTest;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class UseThreadPool{
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        for(int i = 0;i<2;i++){
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    try {
                        //Thread.sleep(1000 * 60 * 60);
                        Thread.sleep(1000 * 1);
                        System.out.println("Thread " + Thread.currentThread().getName() + " finished task");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            threadPool.execute(task);
        }

        threadPool.shutdown();
    }
}
