package com.kelvin.kelvinTestProjectMaven.threadTest.coCurrency;

import java.util.concurrent.locks.ReentrantLock;

public class EvenGeneratorMutex extends IntGenerator{
	private int currentEvenValue = 0;
	private ReentrantLock lock = new ReentrantLock();
	@Override
	public int next() {
		lock.lock();
		try{
			++this.currentEvenValue;
			Thread.yield();
			++this.currentEvenValue;
			return this.currentEvenValue;
		}
		finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args){
		EvenChecker.test(new EvenGeneratorMutex());
	}
}
