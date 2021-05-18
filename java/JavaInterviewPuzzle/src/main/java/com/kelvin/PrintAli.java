package com.kelvin;

import java.io.IOException;
import java.util.Scanner;

/**
 * （JAVA）有3个独立的线程，一个只会输出A，一个只会输出L，一个只会输出I。
 * 在三个线程同时启动的情况下，请用合理的方式让他们按顺序打印ALIALI。
 * 三个线程开始正常输出后，主线程若检测到用户任意的输入则停止三个打印线程的工
 * 作，整体退出。
 *
 */
public class PrintAli
{
    public static volatile boolean stop = false;

    private static Object lockA = new Object();
    private static Object lockB = new Object();
    private static Object lockC = new Object();

    public static void main(String[] args) throws IOException, InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override

            public void run() {
                while(!Thread.interrupted()){
                    try {
                        synchronized (lockA){
                            lockA.wait();
                        }

                        synchronized (lockB){
                            System.out.print("A");
                            Thread.sleep(1000);
                            lockB.notify();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override

            public void run() {
                while(!Thread.interrupted()){
                    try {
                        synchronized (lockB){
                            lockB.wait();
                        }

                        synchronized (lockC){
                            System.out.print("L");
                            Thread.sleep(1000);
                            lockC.notify();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }, "t2");

        Thread t3 = new Thread(new Runnable() {
            @Override

            public void run() {
                while(!Thread.interrupted()){
                    try {
                        synchronized (lockC){
                            lockC.wait();
                        }

                        synchronized (lockA){
                            System.out.println("I");
                            Thread.sleep(1000);
                            lockA.notify();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }, "t3");


        System.out.println("begin thread...");
        t1.start();
        t2.start();
        t3.start();

        Object lock = lockA;
        synchronized(lock){
            lock.notify();
        }

        Scanner s = new Scanner(System.in);
        String input = s.next();
        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
//        System.out.println(input);
//        stop = true;
    }
}
