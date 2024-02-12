package com.springboot_jwt.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RequestDto {

    @NotBlank(message = "First Name can't Be null")
    private String name;

    @NotBlank(message = "Email can't Be null")
    @Email(message = "Email should contain @ ")
    private String email;

    @NotBlank(message = "Password Must Be Required")
    @Size(min = 6, message = "Password Must Be 6")
    private String password;

    @NotBlank(message = "Role Must Be Required")
    private String roles;
}
