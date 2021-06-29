package com.kelvin.kelvinTestProjectMaven.threadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseConditionAndLock {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        List<Integer> resources = new ArrayList<Integer>();

        Thread tProducer = new Thread(new Producer(lock, resources, condition));
        Thread tConsumer = new Thread(new Consumer(lock,resources,condition));
        tConsumer.start();
        tProducer.start();
    }

    public static class Producer implements Runnable{
        private Lock lock;
        private List<Integer> resources;
        private Condition condition;

        public Producer(Lock lock, List<Integer> resources, Condition condition) {
            this.lock = lock;
            this.resources = resources;
            this.condition = condition;
        }

        @Override
        public void run() {
            while(true){
                try{
                    lock.lock();
                    if(this.resources.size() == 5){
                        System.out.println("resource is full, wait for consuming...");
                        condition.await();
                    }else{
                        int i = getNextResourceNumber(this.resources);
                        resources.add(i);
                        System.out.println("resources " + i + " was created");
                        condition.signalAll();
                    }
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

        private int getNextResourceNumber(List<Integer> resources){
            int i = 0;
            int lastOne = 0;
            if(resources.size() == 0)
                i = 1;
            else {
                lastOne = resources.get(resources.size() - 1);
                if(5 == lastOne)
                    i = 1;
                else
                    i = lastOne + 1;
            }
            return i;
        }
    }

    public static class Consumer implements  Runnable{
        private Lock lock;
        private List<Integer> resources;
        private Condition condition;

        public Consumer(Lock lock, List<Integer> resources, Condition condition) {
            this.lock = lock;
            this.resources = resources;
            this.condition = condition;
        }

        @Override
        public void run() {
            while(true){
                try{
                    lock.lock();
                    if(resources.size() > 0){
                        int resource = this.resources.remove(0);
                        System.out.println("resource " + resource + " was consumed.");
                        condition.signalAll();
                    }
                    else{
                        System.out.println("resources is empty, wait for producing...");
                        condition.await();
                    }
                    TimeUnit.SECONDS.sleep(1);
                }
                catch(InterruptedException ex){
                    ex.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
            }
        }
    }
}
