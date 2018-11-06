package com.kelvin.test;

public class WhichClassLoader1 {

	public static void main(String[] args) throws javax.naming.NamingException {
		// Get classpath values
		String bootClassPath = System.getProperty("sun.boot.class.path");
		String extClassPath = System.getProperty("java.ext.dirs");
		String appClassPath = System.getProperty("java.class.path");
		// Print them out
		System.out.println("Bootstrap classpath =" + bootClassPath + "\n");
		System.out.println("Extensions classpath =" + extClassPath + "\n");
		System.out.println("Application classpath=" + appClassPath + "\n");
		// Load classes
		Object obj = new Object();
		String str1 = "abc";
		WhichClassLoader1 wcl1 = new WhichClassLoader1();
		WhichClassLoader2 wcl2 = new WhichClassLoader2();
		// Who loaded what?
		System.out.println("Object was loaded by "
		+ obj.getClass().getClassLoader());
		System.out.println("String was loaded by "
				+ str1.getClass().getClassLoader());
		System.out.println("WhichClassLoader1 was loaded by "
		+ wcl1.getClass().getClassLoader() + ",\n its parent class loader is: " + 
				wcl1.getClass().getClassLoader().getParent());
		System.out.println("WhichClassLoader2 was loaded by "
		+ wcl2.getClass().getClassLoader());
		wcl2.getTheClass();
		}

}
