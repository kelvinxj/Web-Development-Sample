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

public class NIOSelectorClient {
    static Selector selector;
    public static void main(String[] args) throws IOException {
        selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(8080));

        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        while(true){
            selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                if(selectionKey.isConnectable()){
                    handleConnect(selectionKey);
                }
                else if(selectionKey.isReadable()){
                    handleRead(selectionKey);
                }
            }
        }
    }

    private static void handleConnect(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel)key.channel();
        if(socketChannel.isConnectionPending()){
            socketChannel.finishConnect();
        }

        socketChannel.configureBlocking(false);
        socketChannel.write(ByteBuffer.wrap("Hello, server, I'm NIO client.".getBytes(StandardCharsets.UTF_8)));
        //means socketChannel is interesting in read event(read from server)
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel)key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        socketChannel.read(byteBuffer);
        System.out.println("client received message: " + new String(byteBuffer.array()));

    }
}
