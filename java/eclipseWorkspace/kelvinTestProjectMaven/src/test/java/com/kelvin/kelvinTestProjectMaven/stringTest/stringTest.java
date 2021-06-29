package com.kelvin.kelvinTestProjectMaven.stringTest;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import static org.junit.Assert.*;
import org.junit.Test;
public class stringTest {
	
	@Test
	public void getStringCodePoint() throws UnsupportedEncodingException{
		//String str = "abc中国";
		String str = "aAbceèéüДмитрий中国";

		//get Char's unicode point: String.codePointAt(index). that is the position of this char in unicode system.
		//from code point to char: StringBuilder.appendCodePoint(codePoint)
		int codePoint = 0;
		String singleChar = "";
		for(int i = 0;i<str.length();i++){
			codePoint = str.codePointAt(i);
			StringBuilder sb = new StringBuilder();
			sb.appendCodePoint(codePoint);

			System.out.println("char " + sb.toString() + "; code point:" + codePoint);
		}
	}

	@Test
	public void stringEncodeDecode() throws UnsupportedEncodingException {
		//encode string: String.getBytes(encodingName)
		String encoding = "utf-8";
		int intVal = 0;
		String str = "Д";
		byte[] contents = str.getBytes(encoding);
		System.out.println("String is: " + str + ", byte array of utf-8 encoding:");
		for(int i = 0; i < contents.length; i++) {
			intVal = (int)contents[i];
			System.out.println((i+1) + ":" + intVal + ", " + Integer.toHexString(intVal));
		}

		System.out.println();
		//decode a string: new String(byteArray, encodingName)
		str = "中国";
		contents = str.getBytes(encoding);
		System.out.println("byte array is:");
		for(int i = 0;i < contents.length; i++){
			System.out.println("[" + i + "]:" + contents[i] + ";" + Integer.toHexString(contents[i]));
		}
		//decode string:
		String myStr = new String(contents, encoding);
		System.out.println("String is: " + myStr);
	}
}
