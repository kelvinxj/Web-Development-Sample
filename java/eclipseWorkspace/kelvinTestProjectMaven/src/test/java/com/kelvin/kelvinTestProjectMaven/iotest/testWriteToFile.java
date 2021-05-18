package com.kelvin.kelvinTestProjectMaven.iotest;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.junit.Test;

public class testWriteToFile {
	
	@Test
	public void testWriteStringToFile() throws IOException{
		String fileName = "string.txt";
		File file = new File(fileName);
		if(file.exists())
			file.delete();
		
		FileOutputStream fs = new FileOutputStream(fileName);
		//if you need to write double byte characters, using utf-8 encoding
		OutputStreamWriter ow = new OutputStreamWriter(fs,"utf-8");
		BufferedWriter bw = new BufferedWriter(ow);
		String text = "abc 123�й�";
		bw.write(text);
		bw.newLine();
		bw.close();
	}

	@Test
	public void testWriteDataToFile() throws IOException {
		DataOutputStream out = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream("data.txt")));
		out.writeDouble(3.14159);
		out.writeUTF("This is a PI");
		out.writeDouble(1.141);
		out.writeUTF("that was the root of 2");
		out.close();
		
		DataInputStream in = new DataInputStream(new FileInputStream("data.txt"));
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
		in.close();
	}

}
