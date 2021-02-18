package com.kelvin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NIOSelectorServer {
    static Selector selector; //多路复用器， 用于管理多个连接
    public static void main(String[] args) {
        try {
            selector = Selector.open();

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);//must set false. it is non blocking for connection.
            serverSocketChannel.bind(new InetSocketAddress(8080));

            //listenning accept event.
            //selection key is like the ID of current socket channel.
            //means serverSocketChannel is intresting in accept event.
            SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while(true){
                selector.select(); //blocked here
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                while(iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove(); //prevent this event is processed twice.

                    if(selectionKey.isAcceptable()){
                        //connected event
                        handleAccpet(selectionKey);
                    }
                    else if(selectionKey.isReadable()){
                        //read event
                        handleRead(selectionKey);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void handleAccpet(SelectionKey key){
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
        try {
            SocketChannel socketChannel = serverSocketChannel.accept(); //here will not return null because connection is ready.
            socketChannel.configureBlocking(false); //set non blocking for IO(read/write)

            socketChannel.write(ByteBuffer.wrap("Hello, client, I'm NIO server with Selector.".getBytes(StandardCharsets.UTF_8)));
            //register "read" event of socket channel.
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel)key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        socketChannel.read(buffer);
        System.out.println("server received message:" + new String(buffer.array()));
    }
}
