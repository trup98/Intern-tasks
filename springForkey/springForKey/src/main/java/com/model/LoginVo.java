package com.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
public class LoginVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Login_id")
    private int id;
    @Column(name = "Firstname")
    private String firstName;
    @Column(name = "Lastname")
    private String lastName;
}
