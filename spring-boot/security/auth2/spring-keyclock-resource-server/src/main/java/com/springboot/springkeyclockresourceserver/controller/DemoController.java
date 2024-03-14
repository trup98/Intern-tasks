package com.springboot.springkeyclockresourceserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/status")
public class DemoController {
    @GetMapping("/admin")
    @PreAuthorize("hasRole('admin')")
    public String admin() {
        return "Hello Admin !";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('user')")
    public String user() {
        return "Hello User !";
    }
}
