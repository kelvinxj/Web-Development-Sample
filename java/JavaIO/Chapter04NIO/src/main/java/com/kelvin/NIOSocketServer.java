package com.kelvin;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class NIOSocketServer {
    public static void main(String[] args) {
        try {
            //server socket channel support blocking and non-blocking.
            ServerSocketChannel server = ServerSocketChannel.open();
            //set to non blocking.
            server.configureBlocking(false);
            server.socket().bind(new InetSocketAddress(8080));
            while(true){
                //when server socket set to non-blocking, accept method is non-blocking.
                SocketChannel clientChannel = server.accept();

                if(clientChannel != null){
                    //server read data from buffer.
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    clientChannel.read(buffer);
                    System.out.println(new String(buffer.array()));

                    //write message to client
                    Thread.sleep(10000);
                    buffer.flip();
                    clientChannel.write(buffer);
                }
                else{
                    Thread.sleep(1000);
                    System.out.println("No client connected.");
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
