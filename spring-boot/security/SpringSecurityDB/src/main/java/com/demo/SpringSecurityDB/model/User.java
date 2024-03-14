package com.demo.SpringSecurityDB.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@ToString
@Table(name = "user_security_db")
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "role")
	private String role;
}
