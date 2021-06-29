package com.kelvin.kelvinTestProjectMaven.threadTest.waitAndNotify;

import java.util.Queue;

public class Consumer implements Runnable{
    private Queue<String> bags;

    public Consumer(Queue<String> bags){
        this.bags = bags;
    }

    @Override
    public void run() {
        while(true){
            synchronized (this.bags){
                //if bags is empty, wait.
                while(bags.isEmpty()){
                    try {
                        System.out.println("bags is empty, wait for producing...");
                        bags.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
//                    int sleepTime = new Random().nextInt(2000);
                    int sleepTime = 500;
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String bag = bags.remove();
                System.out.println("Consumed bag: " + bag);
                //how to do after consumed a bag?
                bags.notifyAll();
            }
        }
    }
}
