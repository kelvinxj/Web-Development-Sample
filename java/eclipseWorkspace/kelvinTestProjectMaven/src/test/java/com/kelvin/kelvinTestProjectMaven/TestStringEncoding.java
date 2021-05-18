package com.kelvin.kelvinTestProjectMaven;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class TestStringEncoding {

	@Test
	public void test() throws UnsupportedEncodingException {
		String str = "abc啊";
		byte[] by = str.getBytes();
		printByte(by);
		
		by = str.getBytes("utf-8");
		printByte(by);
		
		by = str.getBytes("utf-16");
		printByte(by);
	}
	
	private void printByte(byte[] by){
		for(int i = 0; i < by.length; i++){
			System.out.println("byte " + i + ": " + by[i]);
		}
	}
}
