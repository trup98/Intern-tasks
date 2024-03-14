package com.springboot.projection_springdatajpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ApiResponse {
    private int status;
    private String message;
    private Object data;
    @JsonIgnore
    private HttpStatus httpStatus;

    public ApiResponse(HttpStatus httpStatus,String message, Object data) {
        this.message = message;
        this.data = data;
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
    }
}
