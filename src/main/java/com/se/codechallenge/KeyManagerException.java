package com.se.codechallenge;

public class KeyManagerException extends RuntimeException {
    public KeyManagerException() { }
    
    public KeyManagerException(String message) {
        super(message);
    }
    
    public KeyManagerException(Throwable cause) {
        super(cause);
    }
    
    public KeyManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
