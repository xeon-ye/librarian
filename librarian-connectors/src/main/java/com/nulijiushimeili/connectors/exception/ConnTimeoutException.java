package com.nulijiushimeili.connectors.exception;

public class ConnTimeoutException extends RuntimeException {

    public ConnTimeoutException() {
        super();
    }

    public ConnTimeoutException(String message) {
        super(message);
    }
}
