package com.kelvin.test.thread;

public class testWaitAndNotify {

	public static void main(String[] args) {
		
		Printer printer = new Printer();
		
		Thread thread1 = new Thread() {

			@Override
			public void run() {
				// call printpages will cause thread1 waiting.
				printer.printPages(120);
			}
			
		};
		thread1.start();
		
		Thread thread2 = new Thread() {

			@Override
			public void run() {
				try {
					Thread.currentThread().sleep(5000);
					printer.addPages(20);
				} 
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		thread2.start();
		System.out.println("code outsite thread1 still can run...");
	}
}

class Printer{
	//initial 100 pages in printer.
	int noOfPapers = 100;
	
	public synchronized void printPages(int numPages) {
		for (int i =0; i < numPages; i++) {
			//print pages
		}
		
		//if balance number of pages are less than user input,
		//then wait for addPages synchronized method
		//and printing will resume after that.
		
		if(this.noOfPapers < numPages) {
			try {
				System.out.println("not enough papers in printers, waiting...");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("After called notify() method number of Paper : " +    this.noOfPapers); 
		System.out.println("Printing complete"); 
	}
	
	public synchronized void addPages(int pageNum) {
		// Adding more Papers in Printer; 
		this.noOfPapers += pageNum;
		// After adding the paper in printer. Notify the Paused thread; 
		notify();
	}
}
