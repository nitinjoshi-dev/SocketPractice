package org.example.socketpractice.blocking;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//This class will read message from system.in and send the message to server one line at a time.
public class ClientHandler {

    private final Socket socket;
    private DataInputStream systemIn = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public ClientHandler(Socket socket) {
        System.out.println("Connection accepted from " + socket.getRemoteSocketAddress());
        this.socket = socket;
    }

    public void sendMessage() {
        try {
            systemIn = new DataInputStream(System.in);
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(
                    socket.getOutputStream());

            String line;
            do {
                line = "";
                while (!line.contains("Over")) {
                    line = systemIn.readLine();
                    out.writeUTF(line + "\n");
                }
                if (line.contains("Over and out"))
                    break;
                line = "";
                String serverMessage = "";
                while (!line.contains("Over")) {
                    line = in.readUTF();
                    serverMessage += line;
                }
                System.out.println("Message from server " + socket.getRemoteSocketAddress() + ":\n" + serverMessage);
            } while (!"Over and out".equals(line));
            System.out.println("Over and out!! Closing connection");

        } catch (IOException e) {
            throw new RuntimeException("Error while reading/writing to server socket " + e.getMessage(), e);
        } finally {
            try {
                socket.close(); //close connection
                in.close();
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
