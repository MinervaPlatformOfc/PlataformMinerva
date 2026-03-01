package com.example.minerva.exception;

public class FailedConnectionException extends RuntimeException {
    public FailedConnectionException(String message) {
        super(message);
    }
}
