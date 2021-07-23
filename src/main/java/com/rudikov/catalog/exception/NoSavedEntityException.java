package com.rudikov.catalog.exception;

public class NoSavedEntityException extends RuntimeException {
    public NoSavedEntityException(String message) {
        super(message);
    }
}
