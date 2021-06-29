package com.kelvin.kelvinTestProjectMaven.threadTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureTest {
    Supplier<String> createValue = new Supplier<String>() {
        @Override
        public String get() {
            return "Krishna";
        }
    };
    Supplier<String> createValue1 = ()-> {return "Krishna";};
    Supplier<String> createValue2 = ()->"Krishna";

    Function<String, String> step1 = new Function<String, String>() {
        @Override
        public String apply(String s) {
            System.out.println("step1 finished.");
            return "step1 " + s;
        }
    };
    Function<String, String> step1_1 = (val)-> "step1 " + val;
    Function<String, String> step1_2 = (val)->{return "step1 " + val;};

    Function<String, String> step2 = (val) ->{
        System.out.println("step2 finished.");
        return "step2 " + val;
    };

    Consumer<String> showValue = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println(s);
        }
    };
    Consumer<String> showValue1 = (s)-> System.out.println(s);
    Consumer<String> showValue2 = (x)->{
        System.out.println(x);
    };

    @Test
    public void useCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cfuture = new CompletableFuture<>();
        cfuture = CompletableFuture.supplyAsync(createValue)
//                CompletableFuture.supplyAsync(() -> "Krishna")
//                        .thenApply(data -> "Shiri " + data);
                .thenApplyAsync(step1);

        //get return value from CompletableFuture synchronosly
        String msg = cfuture.get();
        System.out.println(msg);

        //get return value from CompletableFuture asynchronosly
//        cfuture.thenAccept(x-> System.out.println(x));
        cfuture.thenAcceptAsync(showValue);
    }

    @Test
    public void useMultipleCompletableFuture(){
        long dt1 = System.currentTimeMillis();
        Function<String, String> step1 = (val)->{
            try {
                Thread.sleep(2000);
                return "step1 " + val;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "";
            }
        };
        Function<String, String> step2 = (val)->{
            try {
                Thread.sleep(2000);
                return "step2 " + val;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "";
            }
        };

        CompletableFuture<Void> cfuture = CompletableFuture.supplyAsync(createValue)
//                CompletableFuture.supplyAsync(() -> "Krishna")
//                        .thenApply(data -> "Shiri " + data);
                .thenApplyAsync(step1)
                .thenApplyAsync(step2)
                .thenAccept(showValue)
                ;

//        cfuture.thenAccept(x-> System.out.println(x));
//        cfuture.thenAcceptAsync(showValue);
        cfuture.join();
        long dt2 = System.currentTimeMillis();
        System.out.println("Total time(sec): " + (dt2 - dt1)/1000.0);
    }

    @Test
    public void bothCompletableFuture(){
        CompletableFuture<Integer> queryOrder = CompletableFuture.supplyAsync(()-> {
            return 1;
        });
        CompletableFuture<String> queryOrderInfo = queryOrder.thenApplyAsync((orderId)->{
            try {
                Thread.sleep(2000);
                return "order id: " + orderId + "; order Info: XXXXX";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "exception occurs in query order info";
            }
        });

        CompletableFuture<List<String>> queryOrderItmes = queryOrder.thenApplyAsync(orderId->{
            try {
                Thread.sleep(2000);
                String[] orderItems = new String[]{"item1","item2","item2"};
                return Arrays.asList(orderItems);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return new ArrayList<String>();
            }
        });

        //use thenCombine and thenAccpet
//        queryOrderInfo.thenCombine(queryOrderItmes,(orderInfo, orderItems)->{
//            return orderInfo + "; order items" + orderItems;
//        }).thenAcceptAsync((val)-> System.out.println("result: " + val)).join();

        //or use thenAcceptBoth
        queryOrderInfo.thenAcceptBoth(queryOrderItmes,(orderInfo, orderItems)->{
            System.out.println("Result is: ");
            System.out.println(orderInfo + "; order items" + orderItems);
        }).join();
    }
}