package com.kelvin.test;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import org.junit.Test;
public class stringTest {
	
	@Test
	public void testString() throws UnsupportedEncodingException{
		String str = new String("abc");
		String str1 = new String("abc");
		String str2 = "abc";
		String str3 = "abc";
		System.out.println(str == str1);
		System.out.println(str == str2);
		System.out.println(str3 == str2);
		
		byte[] bArray = str.getBytes();
		bArray = str.getBytes("UTF-16");
		
		System.out.println(str.indexOf(99));
		
		
		String str5 = "aabc";
		System.out.println(str5.indexOf("a"));
		
		System.out.println(Character.toLowerCase(133));
		
		String str6 = " ";
		System.out.println(str6.getBytes());
	}
	
	@Test
	public void testStringTokenizer(){
		String line = "public void map(LongWritable key, Text value, Context context)";
		StringTokenizer st = new StringTokenizer(line);
		while(st.hasMoreTokens()){
			System.out.println(st.nextToken());
		}
	}
}
