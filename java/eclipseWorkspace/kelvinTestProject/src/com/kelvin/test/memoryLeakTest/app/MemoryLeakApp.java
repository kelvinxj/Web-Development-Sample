package com.kelvin.test.memoryLeakTest.app;

import java.util.ArrayList;
import java.util.Random;

public class MemoryLeakApp {
	private static Random random = new Random();
	public static final ArrayList<Double> list = new ArrayList<Double>(1000000);

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello, we are going to test memory leak");
	    doTest();
//	    System.gc();
		for(;;)
			;
	}
	
	public static void doTest(){
//		ArrayList<Double> list = new ArrayList<Double>(1000000);
		for (int i = 0; i < 2000000; i++) {
	        list.add(random.nextDouble());
	    }
		System.out.println("colleciton was filled");
	}

}
