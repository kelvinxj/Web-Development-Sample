package com.kelvin.test.iotest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;


public class readFromRelativePath {

	public static void main(String[] args) throws IOException {
		
		//run this program:
		//use ant to package this file to a jar.
		//put myPropertyOutSide.properties file at the same path of this jar file.
		//run jar:
		//java -cp test1.jar com.kelvin.test.iotest.readFromRelativePath
			Properties properties = new Properties();
			String currentPath = System.getProperty("user.dir");
			System.out.println("Current execution path: " + currentPath);
			//Read properties file
			BufferedReader bufferedReader = new BufferedReader(new FileReader(currentPath + "\\myPropertyOutSide.properties"));
			properties.load(bufferedReader);
			
			Set<Object> keySet = properties.keySet();
			for(Object key: keySet){
				System.out.println("key : " + key + "; value=" + properties.getProperty(key.toString()));
			}
			
		/*
		 * Is is better to use Properties util to load from a properties file which is placed out of java project
			ResourceBundle bundle = ResourceBundle.getBundle("myPropertyOutSide");
			Enumeration<String> keyIterator = bundle.getKeys();
			
			String key = "";
			while(keyIterator.hasMoreElements()){
				key = keyIterator.nextElement();
				System.out.println("Key=" + key + "; Value=" + bundle.getString(key));
			}
			*/
	}

}
