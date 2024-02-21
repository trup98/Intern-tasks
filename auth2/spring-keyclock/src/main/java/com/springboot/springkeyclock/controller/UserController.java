package com.springboot.springkeyclock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/home")
    public String hello() {
        return "Welcome From Keyclock !";
    }
}
