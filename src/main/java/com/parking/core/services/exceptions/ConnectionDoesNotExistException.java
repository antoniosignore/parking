package com.parking.core.services.exceptions;

public class ConnectionDoesNotExistException extends RuntimeException {
    public ConnectionDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public ConnectionDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionDoesNotExistException(String message) {
        super(message);
    }

    public ConnectionDoesNotExistException() {
    }
}
