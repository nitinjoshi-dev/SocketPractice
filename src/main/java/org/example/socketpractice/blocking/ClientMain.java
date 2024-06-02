package org.example.socketpractice.blocking;

public class ClientMain {


    public static void main(String[] args) {
        Client client = new Client(6666);
        client.start();
    }
}
