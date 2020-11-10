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
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import org.junit.Test;

//import com.ibm.jvm.io.ConsoleInputStream;
public class testReadFromPropertyFile {	
	
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
	public void testReadPropertiesFromJarFile() throws ClassNotFoundException, IllegalAccessException, InstantiationException{
		//bundleInJar.properties file is in myProperty.jar
		//myProperty.jar is in this projects's class path(Libs project.)
		
		//if bundleInJar.properties in both myProperty.jar and myProperty2.jar
		//property with the same key in first jar loaded by java program will be used
		//for this function bundle.getString("fileName") will show myProperty.zip or myProperty2.zip
		ResourceBundle bundle = ResourceBundle.getBundle("bundleInJar");
		Enumeration<String> keyIterator = bundle.getKeys();
		
		String key = "";
		while(keyIterator.hasMoreElements()){
			key = keyIterator.nextElement();
			System.out.println("Key=" + key + "; Value=" + bundle.getString(key));
		}
	}
	
	@Test
	public void testReadPropertiesFromAnywhereByAbsolutePath() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
		Properties properties = new Properties();
		//Read properties file
		BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\IBM_ADMIN\\Documents\\MyGit\\MySource\\java\\eclipseWorkspace\\properties\\myPropertyOutSide.properties"));
		properties.load(bufferedReader);
		
		Set<Object> keySet = properties.keySet();
		for(Object key: keySet){
			System.out.println("key: " + key + "; value=" + properties.getProperty(key.toString()));
		}
	}
}
