package com.app;

public class NotExistedTableException extends Exception {
    public NotExistedTableException(String message) {
        super(message);
    }
}
