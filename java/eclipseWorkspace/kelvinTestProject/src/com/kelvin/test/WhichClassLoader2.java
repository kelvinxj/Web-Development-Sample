package com.kelvin.test;

public class WhichClassLoader2 {
	// This method is invoked from WhichClassLoader1
	public void getTheClass() {
	WhichClassLoader3 wcl3 = new WhichClassLoader3();
	System.out.println("WhichClassLoader3 was loaded by "
	+ wcl3.getClass().getClassLoader());
	}
}
