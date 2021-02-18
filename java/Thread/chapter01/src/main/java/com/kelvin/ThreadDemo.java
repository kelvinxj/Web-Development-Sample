package com.kelvin;

public class ThreadDemo extends Thread{
    @Override
    public void run() {
        System.out.println("Current thread:" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ThreadDemo thread1 = new ThreadDemo();
        thread1.start();
    }
}
