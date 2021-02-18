package com.kelvin.serverClientDemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketClass {
    //start a server
    public static void main(String[] args) {
        final int DEFAULT_PORT = 8080;

        try {
            ServerSocket server = new ServerSocket(DEFAULT_PORT);
            while(true){

                //blocking. waiting for client connect.
                Socket socket = server.accept();
                System.out.println("client from port" + socket.getPort() + " is connected to server");
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                String line = reader.readLine();
                System.out.println("Received from client: " + line);
                Thread.sleep(15000);

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                //need to add new line to tell client the message sent is completed.
                //if no newline, client thinks the server don't complete with message sent and will keep read from server.
                writer.write("server received message\n");
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
