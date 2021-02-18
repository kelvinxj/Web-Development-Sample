package com.kelvin;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8080);
            //send some data to server.
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("Hello, world".getBytes("utf-8"));
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
