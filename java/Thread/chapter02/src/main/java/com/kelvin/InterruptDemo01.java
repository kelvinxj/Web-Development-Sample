package com.kelvin;

import java.util.concurrent.TimeUnit;

public class InterruptDemo01 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            int i = 0;
            System.out.println(Thread.currentThread().getName() + " started. i=" + i);
            //if current is not interrupted, it will go on running.
            while(!Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println(Thread.currentThread().getName() + " is interrupted. i=" + i);
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
