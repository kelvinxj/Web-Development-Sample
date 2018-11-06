package com.kelvin.test.iotest;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.junit.Test;

public class TestNewIO {
	private String fileName = "newio.txt";
	private String copyToFileName = "copynewio.txt";
	//The size of buffer, in unit of byte
	private int BSIZE = 1024;
	
	@Test
	public void readFileNewIO() throws IOException {
		
		//Write a file
		FileChannel fc = 
				new FileOutputStream(fileName).getChannel();
		fc.write(ByteBuffer.wrap("some text啊 ".getBytes()));
		fc.close();
		
		//Add to the end of the file
		fc = new RandomAccessFile(fileName,"rw").getChannel();
		fc.position(fc.size());//Move to the end
		fc.write(ByteBuffer.wrap("Some more".getBytes()));
		fc.close();
		
		//Read the file
		fc = new FileInputStream(fileName).getChannel();
		ByteBuffer bf = ByteBuffer.allocate(BSIZE);
		fc.read(bf);
		bf.flip();
		int i = 0;
		while(bf.hasRemaining()){
			i++;
			//input one byte each time. So the unicode will be broken
			System.out.println("Byte " + i + ": " + (char)bf.get());
		}
	}
	
	@Test
	public void copyFileByChannel() throws IOException{
		FileChannel in = new FileInputStream(fileName).getChannel();
		FileChannel out = new FileOutputStream(copyToFileName).getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1);
		
		while(in.read(buf) != -1){
			//Prepare for writing
			buf.flip();
			out.write(buf);
			//Prepare for reading
			buf.clear();
		}
		
		in.close();
		out.close();		
	}
	
	@Test
	public void copyFileByChannelByTransfer() throws IOException{
		FileChannel in = new FileInputStream(fileName).getChannel();
		FileChannel out = new FileOutputStream(copyToFileName).getChannel();
		
		
		out.transferFrom(in, 0, in.size());
		//or:
		//in.transferTo(0, in.size(), out);
		
		in.close();
		out.close();
	}
	
	@Test
	public void BufferToText() throws IOException{
		String fileName = "dataout.txt";
		FileChannel out = new FileOutputStream(fileName).getChannel();
		out.write(ByteBuffer.wrap("some text啊".getBytes()));
		out.close();
		
		FileChannel in = new FileInputStream(fileName).getChannel();
		ByteBuffer buf = ByteBuffer.allocate(BSIZE);
		in.read(buf);
		buf.flip();
		//Doesn't work:
		System.out.println("Content in " + fileName + ":" + buf.asCharBuffer());
		
		//Using default encoding:
		//go back to the beginning of data
		buf.rewind();
		String encodingName = System.getProperty("file.encoding");
		//System.out.println(encodingName);
		System.out.println("Decode using " + encodingName + ": " + Charset.forName(encodingName).decode(buf));
		
		//or, we could encode something that can be print as reasonable string:
		out = new FileOutputStream(fileName).getChannel();
		String encoding = "UTF-16";
		out.write(ByteBuffer.wrap("some text啊".getBytes(encoding)));
		out.close();
		
		in = new FileInputStream(fileName).getChannel();
		buf.clear();
		in.read(buf);
		buf.flip();
		//Will work
		System.out.println(buf.asCharBuffer());
		//or decode by the same encoding
		//System.out.println(Charset.forName(encoding).decode(buf));
		
		//use CharBuffer to write through:
		out = new FileOutputStream(fileName).getChannel();
		buf = ByteBuffer.allocate(24);
		buf.asCharBuffer().put("some text啊");
		out.write(buf);
		out.close();
		
		in = new FileInputStream(fileName).getChannel();
		buf.clear();
		in.read(buf);
		buf.flip();
		System.out.println(buf.asCharBuffer());
		
	}
	
	@Test
	public void fetchPrimitives(){
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		// Allocation automatically zeroes the ByteBuffer:
		// So it will not print anything to console
		int i = 0;
		while(i++ < bb.limit())
			if(bb.get() != 0)
				System.out.println("nonzero");
		
		System.out.println("i = " + i);
		bb.rewind();
		// Store and read a char array:
		bb.asCharBuffer().put("Howdy啊!");
		char c;
		while((c = bb.getChar()) != 0)
			System.out.print(c + " ");
		System.out.println();
		bb.rewind();
		// Store and read a short:
		bb.asShortBuffer().put((short)471142);
		System.out.println(bb.getShort());
		bb.rewind();
		// Store and read an int:
		bb.asIntBuffer().put(99471142);
		System.out.println(bb.getInt());
		bb.rewind();
		// Store and read a long:
		bb.asLongBuffer().put(99471142);
		System.out.println(bb.getLong());
		bb.rewind();
		// Store and read a float:
		bb.asFloatBuffer().put(99471142);
		System.out.println(bb.getFloat());
		bb.rewind();
		// Store and read a double:
		bb.asDoubleBuffer().put(99471142);
		System.out.println(bb.getDouble());
		bb.rewind();
	}
	
	@Test
	public void TestIntBuffer(){
		ByteBuffer by = ByteBuffer.allocate(BSIZE);
		IntBuffer ib = by.asIntBuffer();
		
		// Store an array of int:
		ib.put(new int[]{ 11, 42, 47, 99, 143, 811, 1016 });
		
		//Absolutely location and write
		System.out.println("The fourth one is: " + ib.get(3));
		ib.put(3,1811);
		System.out.println("Before flip(), Limit: " + ib.limit() + "; position: " + ib.position());
		
		//setting a new limit before rewrinding the buffer
		ib.flip();
		System.out.println("After flip(), Limit: " + ib.limit() + "; position: " + ib.position());
		while(ib.hasRemaining()){
			int i = ib.get();
			System.out.print(i + " ");
		}
	}
	
	@Test
	public void testViewBuffer(){
		//Print as byte buffer
		ByteBuffer bb = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, 0, 0, 0, 'a'});
		bb.rewind();
		System.out.print("Byte buffer ");
		while(bb.hasRemaining()){
			System.out.print(bb.position() + " -> " + bb.get() + ", ");
		}
		System.out.println();
		
		//Print as char buffer
		CharBuffer cb = 
				((ByteBuffer)bb.rewind()).asCharBuffer();
		System.out.print("Char buffer ");
		while(cb.hasRemaining()){
			System.out.print(cb.position() + " -> " + cb.get() + ", ");
		}
		System.out.println();
		
		//Print as float buffer
		FloatBuffer fb = 
				((ByteBuffer)bb.rewind()).asFloatBuffer();
		System.out.print("Float buffer ");
		while(fb.hasRemaining()){
			System.out.print(fb.position() + " -> " + fb.get() + ", ");
		}
		System.out.println();
		
		//Print as int buffer
		IntBuffer ib = 
				((ByteBuffer)bb.rewind()).asIntBuffer();
		System.out.print("int buffer ");
		while(ib.hasRemaining()){
			System.out.print(ib.position() + " -> " + ib.get() + ", ");
		}
		System.out.println();
		
		//Print as Long buffer
		LongBuffer lb = 
				((ByteBuffer)bb.rewind()).asLongBuffer();
		System.out.print("Long buffer ");
		while(lb.hasRemaining()){
			System.out.print(lb.position() + " -> " + lb.get() + ", ");
		}
		System.out.println();
		
		//Print as short buffer
		//Print as double buffer
		
	}
	
	@Test
	public void testFlipAndRewind()
	{
		ByteBuffer bf = ByteBuffer.allocate(BSIZE);
		IntBuffer ib = bf.asIntBuffer();
		
		//using IntBuffer.put()
		//flip will work
		ib.put(new int[]{1,2,3});
		System.out.println("Initialize bytefuffer by put():");
		System.out.println("Position is: " + ib.position() + " ; Limit is: " + ib.limit() + " ; Capacity is: " + ib.capacity());
		
		ib.flip();		
		System.out.println("Using flip():");
		System.out.println("Position is: " + ib.position() + "; Limit is: " + ib.limit());
		while(ib.hasRemaining()){
			System.out.print(ib.get() + " ");
		}
		System.out.println();

		//rewind don't work
		System.out.println();
		bf = ByteBuffer.allocate(BSIZE);
		ib = bf.asIntBuffer();
		ib.put(new int[]{1,2,3});
		System.out.println("Position is: " + ib.position() + " ; Limit is: " + ib.limit() + " ; Capacity is: " + ib.capacity());
		
		ib.rewind();
		System.out.println("Using rewind():");
		System.out.println("Position is: " + ib.position() + "; Limit is: " + ib.limit());
		while(ib.hasRemaining()){
			System.out.print(ib.get() + " ");
		}
		
		//using IntBuffer.wrap()
		System.out.println("");
		System.out.println("");
		ib = IntBuffer.wrap(new int[]{1,2,3});
		System.out.println("Initialize bytefuffer by wrap():");
		System.out.println("Position is: " + ib.position() + " ; Limit is: " + ib.limit() + " ; Capacity is: " + ib.capacity());
		
		//flip will not work
		ib.flip();
		System.out.println("Using flip():");
		System.out.println("Position: " + ib.position() + "; Limit: " + ib.limit());
		while(ib.hasRemaining()){
			System.out.print(ib.get() + " ");
		}

		System.out.println();
		ib = IntBuffer.wrap(new int[]{1,2,3});
		System.out.println("Initialize bytefuffer by wrap():");
		System.out.println("Position is: " + ib.position()+ " ; Limit is: " + ib.limit() + " ; Capacity is: " + ib.capacity());
		ib.rewind();
		
		//rewind will work
		System.out.println("Using rewind():");
		System.out.println("Position is: " + ib.position() + "; Limit is: " + ib.limit());
		while(ib.hasRemaining()){
			System.out.print(ib.get() + " ");
		}
	}
}
