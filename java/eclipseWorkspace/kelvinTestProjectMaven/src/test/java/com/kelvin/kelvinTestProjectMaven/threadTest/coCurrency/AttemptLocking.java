package com.kelvin.kelvinTestProjectMaven.threadTest.coCurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
	private ReentrantLock lock = new ReentrantLock();
	public void untimed(){
		boolean captured = lock.tryLock();
		
		try{
			System.out.println("trylock(): " + captured);
		}
		finally{
			if(captured)
				lock.unlock();
		}
	}
	
	public void timed(){
		boolean captured = false;
		try{
			captured = lock.tryLock(2, TimeUnit.SECONDS);
			System.out.println("trylock(2,TimeUnit.SECONDS): " + captured);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(captured)
				lock.unlock();
		}
	}
	
	public static void main(String[] args){
		final AttemptLocking al = new AttemptLocking();
		al.untimed();
		al.timed();
		
		new Thread(){
			{this.setDaemon(true);}
			
			public void run(){
				al.lock.lock();
				System.out.println("acquired");
			}
		}.start();
		Thread.yield();
		al.untimed();
		al.timed();
	}
}
