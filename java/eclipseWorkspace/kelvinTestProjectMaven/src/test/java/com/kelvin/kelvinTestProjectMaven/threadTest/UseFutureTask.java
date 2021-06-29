package com.kelvin.kelvinTestProjectMaven.threadTest;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class UseFutureTask {
    public static class CalculationCallable implements Callable<Integer> {
        private int x,y;
        public CalculationCallable(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public Integer call() throws Exception {
            System.out.println("begin call:"+new Date());
            TimeUnit.SECONDS.sleep(2); //模拟任务执行的耗时
            return x+y;
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new CalculationCallable(1,2));
        Thread thread1 = new Thread(futureTask);
        thread1.start();
        System.out.println("begin execute future task:"+new Date());
        //get是异步方法，会阻塞
        int result = futureTask.get();
        System.out.println("result:"+result+"");
        System.out.println("end execute future task:"+new Date());
    }
}
