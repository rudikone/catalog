package com.rudikov.catalog.exception.advice;

import com.rudikov.catalog.exception.NoFoundEntityException;
import com.rudikov.catalog.exception.NoSavedEntityException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NoFoundEntityException.class})
    public ResponseEntity<Object> handleNoFoundEntityException(
            NoFoundEntityException e, WebRequest request) {
        return new ResponseEntity<Object>(
                e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoSavedEntityException.class})
    public ResponseEntity<Object> handleNotFoundDepartmentException(
            NoSavedEntityException e, WebRequest request) {
        return new ResponseEntity<Object>(
                e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


}
