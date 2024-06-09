package org.example.socketpractice.nonblocking.http;

import org.example.socketpractice.nonblocking.IMessageReader;
import org.example.socketpractice.nonblocking.IMessageReaderFactory;

public class HttpMessageReaderFactory implements IMessageReaderFactory {

    public HttpMessageReaderFactory() {
    }

    @Override
    public IMessageReader createMessageReader() {
        return new HttpMessageReader();
    }
}
