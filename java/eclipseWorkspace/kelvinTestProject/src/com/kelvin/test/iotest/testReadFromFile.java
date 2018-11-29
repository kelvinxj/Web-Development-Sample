package com.kelvin.test.iotest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

import com.ibm.jvm.io.ConsoleInputStream;
public class testReadFromFile {	
	@Test
	public void testReadNonPropertyFileByLine() throws IOException {
		String filename = "./file.txt"; //file should be at root path of this project, that is, file.txt should be at same level with java source file "src" folder
		
		FileInputStream fs = new FileInputStream(filename);
		
		//if the file is encoded by UTF-8, should use UTF-8 to create inputStreamReader
		//should also make sure eclipse console encoding utf-8
		InputStreamReader isr = new InputStreamReader(fs,"UTF-8");
//		InputStreamReader isr = new InputStreamReader(fs);
		//InputStreamReader isr = new InputStreamReader(fs);
		BufferedReader bf = new BufferedReader(isr);
		
		//read file by line
		//Eclipse console may not support UTF-8 encoding. You can debug to see the
		//Non-ASCII characters
		String line = "";
		System.out.println("Method 1: using InputStreamReader:");
		int lineNumber = 0;
		while((line = bf.readLine()) != null){
			System.out.println("Line " + (++lineNumber) + ":" + line);
		}
		
		//only need to call BufferedReader.close, as it will close underlined InputStream' close()
		bf.close();
		
		//FileReader don't support encoding, so it will break unicode data.
		//the only way to make it not break unicode data is change source code file 
		//decode to unicode decoding(utf-8 or utf-16): eclipse Edit->Set Encoding to choose encoding
		bf = new BufferedReader(new FileReader(filename));
		System.out.println("Method 2: using FileReader(unicode data broken):");
		while((line = bf.readLine()) != null){
			System.out.println(line);
		}
		bf.close();
		
		String encoding = ConsoleInputStream.getEncoding(new FileInputStream(filename));
		System.out.println(encoding);
	}
	
	@Test
	public void testPrintWriter(){
		//set AutoFlush to true to make the text to console
		PrintWriter pw = new PrintWriter(System.out, true);
		pw.println("abc");
	}
	
	@Test
	public void testReadNonPropertyFileByLineFromStringReader() throws IOException {
		//string buffer to store file content;
		StringBuilder sb = new StringBuilder();
		
		String filename = "./file.txt"; //file should be at root path of this project		
		FileInputStream fs = new FileInputStream(filename);
		
		InputStreamReader isr = new InputStreamReader(fs, "utf-8");
		//Not use utf-8 will break the character to read
		//InputStreamReader isr = new InputStreamReader(fs);
		BufferedReader br = new BufferedReader(isr);
		String line = "";
		while((line = br.readLine()) != null){
			sb.append(line);
		}
		br.close();
		
		StringReader sr = new StringReader(sb.toString());
		int c;
		int i = 0;
		while((c = sr.read()) != -1){
			i++;
			System.out.println("Char " + i + " value: " + c + "; Character: " + (char)c);
		}
	}
	
	@Test
	public void testReadFromPropertiesInPackage() throws ClassNotFoundException, IllegalAccessException, InstantiationException{
		ResourceBundle bundle = ResourceBundle.getBundle("com.kelvin.test.iotest.myProperty");
		Enumeration<String> keyIterator = bundle.getKeys();
		
		String key = "";
		while(keyIterator.hasMoreElements()){
			key = keyIterator.nextElement();
			System.out.println("Key=" + key + "; Value=" + bundle.getString(key));
		}
		
//		Locale locale = Locale.getDefault();
//		ClassLoader classLoader = VM.callerClassLoader();
//		Class<?> bundleClass = Class.forName("com.kelvin.test.iotest.myProperty", true, this.getClass().getClassLoader());
//        if (ResourceBundle.class.isAssignableFrom(bundleClass)) {
//            bundle = (ResourceBundle) bundleClass.newInstance();
//        }
		
		URL url = this.getClass().getClassLoader().getResource("");
		url = Thread.currentThread().getContextClassLoader().getResource("");
	}
	
	@Test
	public void testReadFromPropertiesInSourceCodeRoot(){
		//mypropertyOutSide.properties should be in src folder
		ResourceBundle bundle = ResourceBundle.getBundle("mypropertyOutSide");
		Enumeration<String> keyIterator = bundle.getKeys();
		
		String key = "";
		while(keyIterator.hasMoreElements()){
			key = keyIterator.nextElement();
			System.out.println("Key=" + key + "; Value=" + bundle.getString(key));
		}		
	}
	
	@Test
	/*
	 * read from file by character. use read(). it will return -1 when reach the end of the file.
	 */
	public void readFileByCharacter() throws IOException{
		String filename = "./file.txt"; //file should be at root path of this project
		
		FileInputStream fs = new FileInputStream(filename);
		InputStreamReader isr = new InputStreamReader(fs,"UTF-8");
		BufferedReader bf = new BufferedReader(isr);
		int s = 0;
		char ch;
		while((s = bf.read()) != -1){
			//ch = (char)s;
			//System.out.println(ch);
			System.out.println(s);
		}
		
		//only need to call BufferedReader.close()
		bf.close();
	}
	
	@Test
	public void readFileBySingleByte() throws IOException{
		String filename = "./file.txt"; //file should be at root path of this project
		
		FileInputStream fs = new FileInputStream(filename);
		int byteValue = 0;
		int offset = 0;
		while((byteValue = fs.read()) != -1){
			offset += 1;
			System.out.println("Byte " + offset + ": " + byteValue + "(" + Integer.toHexString(byteValue) + ")");
		}
	}
	
	@Test
	public void readFileRandomly() throws IOException{
		RandomAccessFile raf = new RandomAccessFile("./file.txt", "r");
		int s = 0;
		long currentPos = 0;
		while((s = raf.read()) != -1){
			currentPos = raf.getFilePointer();
			System.out.println("current position: " + currentPos + ": byte: " + s);
		}
		
		//read by two bytes, not a good method to use when file has single byte and multiple byte data.
//		System.out.println("read file by character:");
//		raf.seek(0);
//		char ch = 0;
//		while((ch = raf.readChar()) != -1){
//			currentPos = raf.getFilePointer();
//			System.out.println("current position: " + currentPos + ": char: " + ch);
//		}
		raf.seek(0);
		String line = "";
		while((line = raf.readLine()) != null){
			System.out.println(line);
		}
		
		System.out.println("print character reversely:");
		long fileLen = raf.length();
		currentPos = fileLen - 1;
		while(currentPos >=0){
			raf.seek(currentPos);
			s = raf.read();
			System.out.println(s);
			currentPos --;
		}
		raf.close();
	}
}
