package com.kelvin.producerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello, Wait and notify demo!");

        Queue<String> bags = new LinkedList<String>();
        int maxSize = 10;

        Thread producerThread = new Thread(new Producer(bags,maxSize));
        Thread consumerThread = new Thread(new Consumer(bags));

        producerThread.start();
        consumerThread.start();
    }
}
