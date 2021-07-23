package com.rudikov.catalog.exception;

public class NoFoundEntityException extends RuntimeException {
    public NoFoundEntityException(String message) {
        super(message);
    }
}
