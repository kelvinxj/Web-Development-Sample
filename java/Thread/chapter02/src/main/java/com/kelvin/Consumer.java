package com.kelvin;

import java.util.Queue;

public class Consumer implements Runnable{

    private Queue<String> bags;

    public Consumer(Queue<String> bags) {
        this.bags = bags;
    }

    @Override
    public void run() {
        while(true){
            synchronized (bags){
                while(bags.isEmpty()){
                    //no bag to consume. wait.
                    //producer will notify this blocked consumer.
                    System.out.println("bag is empty.");
                    try {
                        bags.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(378);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String bag = bags.remove();
                System.out.println("Consumed " + bag);
                bags.notifyAll();
            }
        }
    }
}
