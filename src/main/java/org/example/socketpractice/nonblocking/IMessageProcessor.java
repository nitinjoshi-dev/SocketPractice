package org.example.socketpractice.nonblocking;

public interface IMessageProcessor {

    public void process(Message message, WriteProxy writeProxy);

}
