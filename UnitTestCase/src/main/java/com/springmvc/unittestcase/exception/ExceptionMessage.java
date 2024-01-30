package com.springmvc.unittestcase.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ExceptionMessage {
    private String message;
    private HttpStatus httpStatus;
}
