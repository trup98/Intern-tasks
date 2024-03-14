package com.springmvc.basic_spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "Welcome Home";
    }

    @GetMapping("/login")
    public String login() {
        return "Welcome to Login Page";
    }
}
