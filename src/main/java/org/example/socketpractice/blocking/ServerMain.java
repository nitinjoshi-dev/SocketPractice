package org.example.socketpractice.blocking;

public class ServerMain {


    public static void main(String[] args) {
        Server server = new Server(6666);
        server.start();
    }
}
