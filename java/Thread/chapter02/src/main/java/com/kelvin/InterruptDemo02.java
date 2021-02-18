package com.kelvin;

import java.util.concurrent.TimeUnit;

public class InterruptDemo02 {
    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while(true){
                i++;
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("before:" + Thread.currentThread().isInterrupted());
                    //set interrupted flag to false;
                    Thread.interrupted();
                    System.out.println("after:" + Thread.currentThread().isInterrupted());
                    System.out.println("i: " + i);
                    break;
                }
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
