package com.springmvc.springbootvalidation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserException(UserNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
//      Creating payload which containing exception details
        UserException userException = new UserException(e.getMessage(), notFound);
//      Return responseEntity
        return new ResponseEntity<>(userException, notFound);
    }
}
