package com.kelvin.kelvinTestProjectMaven.iotest;


import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.junit.Test;
public class TestNewIOBuffer {
	final int BSIZE = 1024;
	
	@Test
	public void testWriteAndReadByteBuffer() throws UnsupportedEncodingException {
		ByteBuffer bf = ByteBuffer.allocate(9);
		bf.put((byte) 1);
		bf.put((byte) 2);
		bf.put((byte) 127);
		
		//after put data to buffer, use flip() to make the buffer to be ready to read by get()
		bf.flip();
		while(bf.hasRemaining()){
			System.out.println(bf.get() + " ");
		}
		System.out.println(bf.position());
		
		//rewind() to make the position to beginning of buffer and don't change the limit.
		//rewind to make the buffer to be ready to be written.
		bf.rewind();
		bf.put((byte) 97);
		bf.put((byte) 98);
		bf.put((byte) 99);
		bf.flip();
		while(bf.hasRemaining()){
			System.out.println(bf.get() + " ");
		}
		System.out.println(bf.position());
		
		bf.rewind();
		System.out.println("Buffer data as char:" + Charset.forName("utf-8").decode(bf));
		
		//Bytebuffer to CharBuffer
		//Method 1: put bytes to byte buffer then decode to charBuffer
		bf.clear();
		bf = ByteBuffer.wrap("abc".getBytes("utf-8"));
		System.out.println(bf.position() + "," + bf.capacity() + "," + bf.limit());
		CharBuffer cb = Charset.forName("utf-8").decode(bf);
		System.out.println(cb.position() + "," + cb.capacity() + "," + cb.limit());
		int i = 0;
		cb.rewind();
		while(cb.hasRemaining()){
			i++;
			char c = cb.get();
			System.out.println("byte " + i + ":" + c);
		}
		
		//method2:
		//put value to charBuffer
		bf = ByteBuffer.allocate(9);
		cb = bf.asCharBuffer();
		cb.put("abc".toCharArray());
		cb.flip();
		i = 0;
		while(cb.hasRemaining()){
			i++;
			char c = cb.get();
			System.out.println("byte " + i + ":" + c);
		}
		cb.flip();
		System.out.println(cb);
		
	}
	
	@Test
	public void readFileByByte() throws IOException{
		int i = 0;
		System.out.println("Read from file without new line");
		FileChannel fc = new FileInputStream("NoEOL.txt").getChannel();
		
		ByteBuffer bf = ByteBuffer.allocate(5);
		int result = fc.read(bf);
		while(result > 0){
			bf.flip();
			while(bf.hasRemaining()){
				i++;
				byte b = bf.get();
				System.out.println("byte " + i + ":" + b + "; char " + i + ": " + (char)b);
			}
			
			//move position to beginning of buffer, prepare to next read
			bf.rewind();
			result = fc.read(bf);
		}
		fc.close();
		
		System.out.println();
		System.out.println("Read from file with Windows new line");
		fc = new FileInputStream("EOLWin.txt").getChannel();
		
		bf.rewind();
		result = fc.read(bf);
		i = 0;
		while(result > 0){
			bf.flip();
			while(bf.hasRemaining()){
				i++;
				byte b = bf.get();
				System.out.println("byte " + i + ":" +b + "; char " + i + ":" + (char)b);
			}
			
			//move position to beginning of buffer, prepare to next read
			bf.rewind();
			result = fc.read(bf);
		}
		fc.close();
		
		System.out.println();
		System.out.println("Read from file with Unix new line");
		fc = new FileInputStream("EOLUnix.txt").getChannel();
		
		bf.rewind();
		result = fc.read(bf);
		i = 0;
		while(result > 0){
			bf.flip();
			while(bf.hasRemaining()){
				i++;
				System.out.println("byte " + i + ":" + bf.get());
			}
			
			//move position to beginning of buffer, prepare to next read
			bf.rewind();
			result = fc.read(bf);
		}
		fc.close();
	}
}
