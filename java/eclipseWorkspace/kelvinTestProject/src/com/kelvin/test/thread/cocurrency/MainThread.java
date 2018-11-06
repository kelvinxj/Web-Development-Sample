package com.kelvin.test.thread.cocurrency;

public class MainThread {

	public static void main(String[] args) {
		Thread t = new Thread(new LiftOff());
		//t.start();
		Thread t1 = new Thread(new LiftOff());
		t1.start();
		t.start();
		System.out.println("Waiting for LiftOff");
	}

}