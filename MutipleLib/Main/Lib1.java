package org.mylib;

public class Lib1 {

	public void add(int i, int j) {
		Lib2.log("Number 1: " + i);
		Lib2.log("Number 2: " + j);
		Lib2.log("Result: " + (i+j));
	}

}
