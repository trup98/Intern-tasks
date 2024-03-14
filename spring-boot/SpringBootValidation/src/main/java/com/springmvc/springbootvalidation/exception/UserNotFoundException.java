package com.springmvc.springbootvalidation.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message)
    {
        super(message);
    }
}
