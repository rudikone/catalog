package com.rudikov.catalog.exception;

public class NotFoundDepartmentException extends Exception{
    public NotFoundDepartmentException(Long id) {
        super("Отдела с id=" + id + " не существует!");
    }
}
