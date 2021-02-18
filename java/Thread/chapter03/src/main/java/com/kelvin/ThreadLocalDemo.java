package com.kelvin;

/**
 * Hello world!
 *
 */
public class ThreadLocalDemo
{
    private static Integer num = 0;

    private static ThreadLocal<Integer> local = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main( String[] args )
    {
        Thread threads[] = new Thread[5];

        for(int i = 0; i<5;i++){
            threads[i] = new Thread(()->{
                int num = local.get();
                num += 5;
                local.set(num);
                System.out.println(Thread.currentThread().getName() + ": num=" + local.get());
            }, "thread " + i);
            threads[i].start();
        }
    }
}
