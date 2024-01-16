package com.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class RegisterVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Register_id")
    private int id;
    @Column(name = "Username")
    private String userName;
    @Column(name = "Password")
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Login_id")
    private LoginVo loginVo;
}
