package com.rudikov.catalog.controllers;

import com.rudikov.catalog.exception.NoEntityException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ NoEntityException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
            NoEntityException e, WebRequest request) {
        return new ResponseEntity<Object>(
                e.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

}
