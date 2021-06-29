package com.kelvin.kelvinTestProjectMaven.threadTest.threadLocal;

public class TestThreadLocal {
	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};
	
	public static class TestClient extends Thread{
		public void run(){
			for(int i = 0;i<3;i++){
				int curValue = seqNum.get();
				seqNum.set(curValue + 1);
				System.out.println("Thread[" + Thread.currentThread().getName() + "] --> sn["
						+ seqNum.get() + "]");
				try {
					//let cpu re-schedule threads. current thread may or may not have
					// cpu time to run after call sleep(0). sleep(0) is the same as yield()
					sleep(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] arg){
		
		TestClient t1 = new TestClient();
		TestClient t2 = new TestClient();
		TestClient t3 = new TestClient();
		
		t1.start();
		t2.start();
		t3.start();
		
	}

}
