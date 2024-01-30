package com.springmvc.unittestcase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonExceptionHandler {
    @ExceptionHandler(value = {PersonException.class})
    public ResponseEntity<Object> handlePersonException(PersonException e) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }
}
