package com.kelvin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFirst {
    public static void main(String[] args) throws IOException {
        String desFileName = "C:\\Users\\xiejia-lihui\\Desktop\\file.txt";
        String srcFileName = "C:\\Users\\xiejia-lihui\\Desktop\\file_cp.txt";
        FileInputStream fis = new FileInputStream(desFileName);
        FileOutputStream fos = new FileOutputStream(srcFileName);

        //NIO channel buffer
        FileChannel fin = fis.getChannel();
        FileChannel fout = fos.getChannel();

        //the buffer should contain all content of the file.
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        fin.read(buffer);
        //buffer: from read to write.
        buffer.flip();
        fout.write(buffer);
        buffer.clear();

        fis.close();
        fos.close();
    }
}
