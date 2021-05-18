package com.kelvin.kelvinTestProjectMaven.threadTest.coCurrency;

public abstract class IntGenerator {
	private volatile boolean canceled = false;
	public void cancel(){this.canceled = true;}
	public boolean isCanceled(){
		return this.canceled;
	}
	
	public abstract int next();
}
