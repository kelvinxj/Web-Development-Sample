package com.kelvin;

import java.util.concurrent.*;

public class CallableDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Current thread:" + Thread.currentThread().getName());
        Thread.sleep(2000);
        return "Hello, world!";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<String> future = service.submit(new CallableDemo());

        //future.get() is a blocking method.
        System.out.println(Thread.currentThread().getName() + ": " + future.get());
        service.shutdown();
    }
}
