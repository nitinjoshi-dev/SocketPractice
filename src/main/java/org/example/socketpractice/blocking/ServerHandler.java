package org.example.socketpractice.blocking;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//This class will read message from client unless it receives "Over" message.
//It will then send a message received response to client
public class ServerHandler implements Runnable {

    private final Socket socket;
    private DataInputStream systemIn = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public ServerHandler(Socket socket) {
        System.out.println("Connection accepted from " + socket.getRemoteSocketAddress());
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            systemIn = new DataInputStream(System.in);
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(
                    socket.getOutputStream());
            String line;
            do {
                line = "";
                String clientMessage  = "";
                while (!line.contains("Over")) {
                    line = in.readUTF();
                    clientMessage += line;
                }
                System.out.println("Message from client " + socket.getRemoteSocketAddress() + ":\n" + clientMessage);
                if (clientMessage.contains("Over and out"))
                    break;
                line = "";
                while (!line.contains("Over")) {
                    line = systemIn.readLine();
                    out.writeUTF(line + "\n");
                }
            } while (!"Over and out".equals(line));
            System.out.println("Over and out!! Closing connection");
        } catch (IOException e) {
            throw new RuntimeException("Error while reading/writing to client socket " + e.getMessage(), e);
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
