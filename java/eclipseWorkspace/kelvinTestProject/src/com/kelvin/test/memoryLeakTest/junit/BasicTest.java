package com.kelvin.test.memoryLeakTest.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

public class BasicTest {
	private Random random = new Random();
	public static final ArrayList<Double> list = new ArrayList<Double>(1000000);
	
	@Test
	public void test() throws InterruptedException {
//		ArrayList<Double> list = new ArrayList<Double>(1000000);
		for (int i = 0; i < 1000000; i++) {
	        list.add(random.nextDouble());
	    }
	    
	    System.gc();
	    Thread.sleep(10000); // to allow GC do its job
	}

}
