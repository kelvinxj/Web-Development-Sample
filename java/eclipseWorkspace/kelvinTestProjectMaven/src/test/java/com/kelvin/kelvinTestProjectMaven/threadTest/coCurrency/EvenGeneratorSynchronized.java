package com.kelvin.kelvinTestProjectMaven.threadTest.coCurrency;
/*
 * this program will not get a non even number
 */
public class EvenGeneratorSynchronized extends IntGenerator{
	private int currentEvenValue = 0;
	@Override
	public synchronized int next() {
		++currentEvenValue;
		Thread.yield();
		++currentEvenValue;
		return this.currentEvenValue;
	}
	
	public static void main(String[] args){
		EvenChecker.test(new EvenGeneratorSynchronized());
	}
}
