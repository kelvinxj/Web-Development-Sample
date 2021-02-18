package com.kelvin.serverClientDemo;

import java.io.*;
import java.net.Socket;

public class ClientSocketClass02 {
    public static void main(String[] args) {
        int DEFAULT_PORT = 8080;
        try {
            Socket socket = new Socket("localhost", DEFAULT_PORT);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            writer.write("This is the message sent from client\n");
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverString = reader.readLine();
            System.out.println("received server message:" + serverString);

            reader.close();
            writer.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
