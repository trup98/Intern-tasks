package com.springboot.springkeyclockresourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DemoController {
    @GetMapping("/working")
    public String demo() {
        return "Working From Secure Server!";
    }
}
