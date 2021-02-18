package com.kelvin;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();

            int len = 0;
            byte[] buffer = new byte[1024];
            while((len = inputStream.read(buffer)) != -1){
                System.out.println(new String(buffer, 0, len, "utf-8"));
            }
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
