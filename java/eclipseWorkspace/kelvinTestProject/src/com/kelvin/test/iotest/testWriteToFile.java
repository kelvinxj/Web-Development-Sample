package com.kelvin.test.iotest;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class testWriteToFile {

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
