package com.kelvin;

public class RunnableDemo implements  Runnable{
    @Override
    public void run() {
        System.out.println("Current thread:" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        RunnableDemo runnableDemo = new RunnableDemo();

        Thread thread1 = new Thread(runnableDemo);
        thread1.start();
    }
}
