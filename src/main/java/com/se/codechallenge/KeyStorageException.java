package com.se.codechallenge;

public class KeyStorageException extends KeyManagerException {
    public KeyStorageException() { }
    
    public KeyStorageException(String message) {
        super(message);
    }
    
    public KeyStorageException(Throwable cause) {
        super(cause);
    }
    
    public KeyStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
