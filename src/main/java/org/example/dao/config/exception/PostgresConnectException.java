package org.example.dao.config.exception;

public class PostgresConnectException extends RuntimeException {
    public PostgresConnectException(String message) {
        super(message);
    }
}
