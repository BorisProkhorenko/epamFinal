package com.epam.web.connection;

public class ConnectionPoolException extends RuntimeException {

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
