package org.example.socketpractice.blocking;

import java.net.Socket;

public class Client {

    private final int port;
    private ClientHandler clientHandler;

    public Client(int port) {
        this.port = port;
    }

    public void start() {
        try (Socket socket = new Socket("localhost", port)) { //Creates, bind to the random port and connects to localhost and port specified.
            System.out.println("Client connected to server " + socket.getRemoteSocketAddress());
            clientHandler = new ClientHandler(socket);
            clientHandler.sendMessage();
        } catch (Exception e) {
            throw new RuntimeException("Error while reading/writing to socket or accepting the connection " + e.getMessage(), e);
        }
    }
}

