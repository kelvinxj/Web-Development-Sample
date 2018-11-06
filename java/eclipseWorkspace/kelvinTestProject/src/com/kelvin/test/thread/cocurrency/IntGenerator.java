package com.kelvin.test.thread.cocurrency;

public abstract class IntGenerator {
	private volatile boolean canceled = false;
	public void cancel(){this.canceled = true;}
	public boolean isCanceled(){
		return this.canceled;
	}
	
	public abstract int next();
}
