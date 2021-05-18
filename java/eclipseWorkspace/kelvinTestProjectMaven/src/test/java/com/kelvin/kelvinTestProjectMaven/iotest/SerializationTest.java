package com.kelvin.kelvinTestProjectMaven.iotest;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;

//this class should also be Serializable as MyObj is a member of SerializationTest
public class SerializationTest implements Serializable{
	
	class MyObj implements Serializable{
		public String name;
		public int score;
		public String show(){
			return "Name: " + name + "; score: " + score;
		}
	}

	@Test
	public void test() throws FileNotFoundException, IOException, ClassNotFoundException {
		MyObj myObj = new MyObj();
		myObj.name = "test";
		myObj.score = 100;
		
		//serialization
		//set this system property to true to show more detail serialization info
		//System.setProperty("sun.io.serialization.extendedDebugInfo", "true");
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./MyObj.out"));
		out.writeObject("this is myObj");
		out.writeObject(myObj);
		out.close();
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("./MyObj.out"));
		String s = (String)in.readObject();
		System.out.println(s);
		
		myObj = (MyObj)in.readObject();
		System.out.println(myObj.show());
		in.close();
	}

}
