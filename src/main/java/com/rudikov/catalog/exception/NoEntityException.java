package com.rudikov.catalog.exception;

public class NoEntityException extends Exception {
    public NoEntityException(Long id) {
        super("Пользователя с id=" + id + " не существует!");
    }
}
