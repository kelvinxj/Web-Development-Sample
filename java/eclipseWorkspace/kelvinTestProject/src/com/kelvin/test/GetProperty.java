package com.kelvin.test;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class GetProperty {
	public static void main(String[] args){
		ResourceBundle bundle = ResourceBundle.getBundle("myPropertyOutSide");
		Enumeration<String> keyIterator = bundle.getKeys();
		
		String key = "";
		while(keyIterator.hasMoreElements()){
			key = keyIterator.nextElement();
			System.out.println("Key=" + key + "; Value=" + bundle.getString(key));
		}
	}
}
