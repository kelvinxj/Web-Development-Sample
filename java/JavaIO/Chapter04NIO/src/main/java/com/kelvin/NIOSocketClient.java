package com.kelvin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NIOSocketClient {
    public static void main(String[] args) {
        try {
            SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(false);
            //In non-blocking model, channel.connect will return immediately.
            channel.connect(new InetSocketAddress("localhost", 8080));
            if(channel.isConnectionPending()){
                channel.finishConnect();
            }

            //client write data to buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("this is string wrote by client.".getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            channel.write(buffer);

            buffer.clear();
            //block 10 seconds if not channel.configureBlocking(false);
            //in non-blocking model, it will return and no data will read.
            int len = channel.read(buffer);
            if(len > 0)
                System.out.println("received message from server: " + new String(buffer.array()));
            else
                System.out.println("not received data from server");
//            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
