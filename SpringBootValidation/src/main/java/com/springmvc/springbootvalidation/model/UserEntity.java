package com.springmvc.springbootvalidation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "User_Validation")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First Name Can't be Empty Or Null")
    private String firstName;

    @NotBlank(message = "Last Name Can't be Empty Or Null")
    private String lastName;

    @NotBlank(message = "Email Must Be Required")
    @Email(message = "Email Must Be Contain '@' ")
    private String email;

    @NotBlank(message = "Password Must Be Required")
    @Size(min = 6,message = "Password Must Be 6")
    private String password;
}
