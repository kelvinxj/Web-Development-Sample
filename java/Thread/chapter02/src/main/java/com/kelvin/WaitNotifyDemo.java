package com.kelvin;

import java.util.LinkedList;
import java.util.Queue;

public class WaitNotifyDemo {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        int size = 10;

        Producer producer = new Producer(queue, size);
        Consumer consumer = new Consumer(queue);

        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);

        threadProducer.start();
        threadConsumer.start();
    }
}
