package com.kelvin.producerConsumer;

import java.util.Queue;
import java.util.Random;

public class Producer implements Runnable{
    private Queue<String> bags;
    private int maxSize;

    public Producer(Queue<String> bags, int maxSize){
        this.bags = bags;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        //produce bad to bags.
        int i = 0;
        while(true){
            synchronized(this.bags){
                while(bags.size() == maxSize){
                    //how to do when bags is full?
                    try {
                        System.out.println("bags is full, wait for consuming...");
                        bags.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //produce a bag
                i++;
                try {
                    //int sleepTime = new Random().nextInt(2000);
                    int sleepTime = 1000;
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bags.add("bag " + i);
                System.out.println("produce bag " + i);
                //how to do after produce a bag?
                bags.notifyAll();
            }
        }
    }
}
