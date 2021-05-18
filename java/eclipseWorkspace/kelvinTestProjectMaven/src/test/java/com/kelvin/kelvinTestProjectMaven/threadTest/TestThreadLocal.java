package com.kelvin.kelvinTestProjectMaven.threadTest;

public class TestThreadLocal {
	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){

		@Override
		protected Integer initialValue() {
			return 0;
		}
		
	};
	
//	private Integer seqNum = 1;
	
	public int getNextNum(){
		seqNum.set(seqNum.get() + 1);
		return seqNum.get();
//		return seqNum++;
	}
	
	public static class TestClient extends Thread{
		private TestThreadLocal sn;
		
		public TestClient(TestThreadLocal sn){
			this.sn = sn;
		}
		
		public void run(){
			for(int i = 0;i<3;i++){
				System.out.println("Thread[" + Thread.currentThread().getName() + "] --> sn["
						+ sn.getNextNum() + "]");
			}
		}
	}
	
	public static void main(String[] arg){
		TestThreadLocal sn = new TestThreadLocal();
		
		TestClient t1 = new TestClient(sn);
		TestClient t2 = new TestClient(sn);
		TestClient t3 = new TestClient(sn);
		
		t1.start();
		t2.start();
		t3.start();
		
	}

}
