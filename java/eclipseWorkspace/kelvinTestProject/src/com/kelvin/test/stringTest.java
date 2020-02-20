package com.kelvin.test;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import static org.junit.Assert.*;
import org.junit.Test;
public class stringTest {
	
	@Test
	public void testStringDecode() throws UnsupportedEncodingException{
		//String str = "abc中国";
		String str = "eèéüДмитрий";
//		byte[] bytes = str.getBytes("utf-8");
//		for(byte ch: bytes){
//			System.out.println(ch);
//		}
		
		for(int i = 0;i<str.length();i++){
			System.out.println(str.codePointAt(i));
		}
		
		String encoding = "utf-8";
		int intVal = 0;
		str = "Д";
		byte[] contents = str.getBytes(encoding);
		System.out.println(str + " byte array by utf-8:");
		for(int i = 0; i < contents.length; i++) {
			intVal = (int)contents[i];
			System.out.println((i+1) + ":" + intVal + ", " + Integer.toHexString(intVal));
		}
		
		str = "张";
		contents = str.getBytes(encoding);
		System.out.println(str + " byte array by utf-8:");
		intVal = 0;
		for(int i = 0; i < contents.length; i++) {
			intVal = (int)contents[i];
			System.out.println((i+1) + ":" + intVal + ", " + Integer.toHexString(intVal));
		}
		
		String myStr = new String(contents, encoding);
		System.out.println(myStr);
	}
	
	@Test
	public void showCharacterAndCodePoint(){
		String characters = "èabД中αñüğçШуy";
		char[] chars = new char[characters.length()];
		characters.getChars(0,characters.length() - 1,chars, 0);
		int codePoint = 0;
		
		for(int i = 0; i < characters.length(); i++){
			codePoint = characters.codePointAt(i);
			System.out.println(String.format("Character: %s, Code point: %s, char: %s"
					,(new StringBuilder()).appendCodePoint(codePoint).toString()
					, codePoint, characters.charAt(i))
					);
		}
	}
	
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
	
	@Test
	public void testSubString(){
		String str = "1234567890";
		//sub string length is: end - start
		String sub1 = str.substring(0,5);
		System.out.println(sub1);
		assertEquals(5,sub1.length());		

		String sub2 = str.substring(2,7);
		System.out.println(sub2);
		assertEquals(5,sub2.length());
	}
}
