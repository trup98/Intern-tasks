package com.springmvc.jdbctemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String passWord;
}
