package org.example.socketpractice.blocking;

import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {
    private final Executor executor;
    private final int port;

    public Server(int port) {
        this.port = port;
        executor = Executors.newFixedThreadPool(1);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) { //Creates and bind to the specified port
            System.out.println("Server started on port " + port);
            while (true) {
                //Accepts the connection and send it to reading and writing to server handler
                executor.execute(new ServerHandler(serverSocket.accept()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while reading/writing to socket or accepting the connection " + e.getMessage(), e);
        }
    }
}

