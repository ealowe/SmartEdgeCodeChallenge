package com.se.codechallenge;

public class MessageEncodingException extends RuntimeException {
    public MessageEncodingException() { }
    
    public MessageEncodingException(String message) {
        super(message);
    }
    
    public MessageEncodingException(Throwable cause) {
        super(cause);
    }
    
    public MessageEncodingException(String message, Throwable cause) {
        super(message, cause);
    }
}
