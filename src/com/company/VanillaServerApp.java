package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class VanillaServerApp {

    public static void streamInputAndPrint(Socket socket) throws IOException {
        // Stream the raw input data (bytes) from the client
        InputStream is = socket.getInputStream();
        try {
            // Add a buffer to the raw input data (bytes) stream
            BufferedInputStream bis = new BufferedInputStream(is);
            // Creats a DataInputStream Object
            // has convenience methods to deserialize the raw input data (bytes) into Java Primitives (e.g String)
            DataInputStream dis = new DataInputStream(bis);
            // Read the input stream of bytes into String
            // Throws an EOFException when the socket is closed
            String line = dis.readUTF();
            System.out.println("line: " + line);
        } catch (EOFException e) {
            // dis.readUTF() throws an EOFException when the client closes the on their end socket.
            // clean up by closing the socket on our end
            socket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        // Create a ServerSocker.
        // Think of it as a part of a server, and is responsible for waiting for requests from a client / user
        // It camps at port 3000 for requests from clients
        // a client would call GET http://localhost:3000 to connect to this
        ServerSocket server = new ServerSocket(3000);

        System.out.println("Vanilla Server Up. Listening at Port 3000.");

        // Blocks (fancy word for wait or suspend this process) until a connection from a client comes in
        Socket socket = server.accept();

        VanillaServerApp.streamInputAndPrint(socket);
    }

}
