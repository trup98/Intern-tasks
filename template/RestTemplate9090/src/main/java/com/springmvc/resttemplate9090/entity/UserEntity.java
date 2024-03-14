package com.springmvc.resttemplate9090.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String address;
}
