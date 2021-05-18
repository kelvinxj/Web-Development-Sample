package com.kelvin.kelvinTestProjectMaven.threadTest;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class MultipleThreadIssues {
	
	@Test
	/*
	 * there are two threads, each thread will increment the counter 10 times.
	 * expected result: the counter's value should be 20
	 */
	public void Issue001UnThreadsafeCounter() throws InterruptedException{
		final Counter counter = new Counter();
		Runnable run = new Runnable(){
			@Override
			public void run() {
				for(int i = 0;i<10;i++){
					try {
						Random rd = new Random();
						int sleepTime = rd.nextInt(13);
						Thread.currentThread().sleep(sleepTime);
						counter.increment();
						sleepTime = rd.nextInt(100);
						Thread.currentThread().sleep(sleepTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		};
		Thread thread1 = new Thread(run, "t1");		
		Thread thread2 = new Thread(run, "t2");
		thread1.start();
		thread2.start();

		thread2.join();
		thread1.join();
		
		System.out.println("counter's value is:" + counter.get());
	}
	
	public class Counter {
		  private int counter = 0;

		  public int get()      { return counter; }
		  public void set(int n) { counter = n; }
		  public void increment() throws InterruptedException {
			  String log = "";
		    //set(get() + 1);
			  //use multiple steps and add sleep() after each step to cause multiple issues more frequent.
			  Thread.currentThread().sleep(3);
			  int i = get();
			  log = Thread.currentThread().getName() + ": current value is:" + i;
			  Thread.currentThread().sleep(5);
			  i++;
			  Thread.currentThread().sleep(1);
			  set(i);
			  log += "; increase to " + i;
			  System.out.println(log);
		  }
	}
	
	@Test
	public void Issue002InsertingDBByMultipleThreading() throws InterruptedException{
		//multiple threads insert same value to a Set<T>.
		//this set simulates database. each thread will check if value exist in set
		//and only insert when value not exist.
		//when value can be inserted, the AtomicInteger indicator will be increased.
		//There is 100 threads, each thread will insert this DB 3 times.
		//
		//expected result: the indicator's value should be one
		//and console will print: DB has been inserted by 1 times
		final Set<Integer> myDb = new HashSet<Integer>();
		final int valueToInsert = 1;
		int totalThreadsCount = 100;
		List<Thread> allThreads = new LinkedList<Thread>();
		final AtomicInteger indicator = new AtomicInteger();
		indicator.set(0);
		
		for(int i = 0;i<totalThreadsCount;i++){
			String name = "t" + i;
			Thread thread = new Thread(new Runnable(){

				@Override
				public void run() {
					try {
						for(int j = 0; j < 3; j++){
							checkAndInsert(myDb, valueToInsert, indicator);
						}
					} 
					catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}}, name);
			allThreads.add(thread);
		}
		
		for(Thread thread: allThreads){
			thread.start();
		}
		
		for(Thread thread: allThreads){
			thread.join();
		}
		System.out.println("DB has been inserted by " + indicator.get() + " times");
	}
	
	/*
	 * This function will executed by multiple thread
	 * add sleep() after each step, will cause multiple thread issue more easyly to occur
	 */
	private boolean  checkAndInsert(Set<Integer> mySet, int value, AtomicInteger indicator) throws InterruptedException{
		boolean result = false;
		//get lock by value
		//lock
//		synchronized(mySet){
			if(mySet.contains(value)){
				//System.out.println(Thread.currentThread().getName() + " failed.");
			}
			else{
				Thread.currentThread().sleep(3);
				mySet.add(value);
				Thread.currentThread().sleep(3);
				indicator.incrementAndGet();
				//System.out.println(Thread.currentThread().getName() + " succeed.");
			}	
//		}
		//unlock
		return result;
	}
}
