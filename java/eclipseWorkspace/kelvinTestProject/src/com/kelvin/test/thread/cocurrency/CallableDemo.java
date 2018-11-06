package com.kelvin.test.thread.cocurrency;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		
		ArrayList<Future<String>> results = 
				new ArrayList<Future<String>>();
		
		for(int i = 0;i<10;i++){
			results.add(exec.submit(new TaskWithResult(i)));
		}
		
		for(Future<String> fs: results){
			try {
				System.out.println(fs.get());
			} 
			catch (Exception e){
				e.printStackTrace();
			}
			/*These two exceptions need JRE 1.7
			catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			*/
			finally{
				exec.shutdown();
			}
		}
	}

}

class TaskWithResult implements Callable<String>{
	private int id;
	
	public TaskWithResult(int id){
		this.id = id;
	}
	@Override
	public String call() throws Exception {
		return "Result of TaskWithResult " + id;
	}
	
}
