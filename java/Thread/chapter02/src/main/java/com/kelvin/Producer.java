package com.kelvin;

import java.util.Queue;

public class Producer implements Runnable{

    private Queue<String> bags;
    private int size;

    public Producer(Queue<String> bags, int size) {
        this.bags = bags;
        this.size = size;
    }

    @Override
    public void run(){
        //produce bags
        int i = 0;
        while(true){
            i++;
            synchronized (bags){
                //bag is full.
                while(bags.size() == size){
                    System.out.println("Bag is full");
                    //todo: what should be done when bag is full? block thread:
                    try {
                        bags.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //produce a bag:
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //todo: what should be done after produce a bag?
                System.out.println("Produce bag " + i);
                bags.add("bag " + i);
                //notify blocked consumer
                bags.notifyAll();
                Thread.yield();
            }
        }
    }
}
