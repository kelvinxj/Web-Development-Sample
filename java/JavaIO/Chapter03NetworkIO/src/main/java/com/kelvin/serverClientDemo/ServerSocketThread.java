package com.kelvin.serverClientDemo;

import java.io.*;
import java.net.Socket;

public class ServerSocketThread implements Runnable{

    private Socket socket;

    public ServerSocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("client from port" + socket.getPort() + " is connected to server");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            String line = reader.readLine();
            System.out.println("Received from client: " + line);
            Thread.sleep(20000);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //need to add new line to tell client the message sent is completed.
            //if no newline, client thinks the server don't complete with message sent and will keep read from server.
            writer.write("server received message\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
