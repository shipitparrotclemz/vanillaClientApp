package com.company;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MyClient {

    public static void writeDataToServer(Socket socket) throws IOException {
        OutputStream os = socket.getOutputStream();
        try {
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);
            // writes "hello world!" to the buffer. the buffer is analogous to a bucket of water
            dos.writeUTF("Hello World!");
            // flush the buffer, analogous to throwing the water out of the bucket
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // connect to the server at localhost:3000
        Socket socket = new Socket("localhost", 3000);

        MyClient.writeDataToServer(socket);

        // Client close the connection
        socket.close();
    }
}
