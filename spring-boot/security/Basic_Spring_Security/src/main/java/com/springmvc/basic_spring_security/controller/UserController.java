package com.springmvc.basic_spring_security.controller;

import com.springmvc.basic_spring_security.model.UserEntity;
import com.springmvc.basic_spring_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public List<UserEntity> searchAll() {
        return this.userService.getAllUser();
    }

    @GetMapping("/user/{userName}")
    public UserEntity userById(@PathVariable String userName) {
        return this.userService.getById(userName);
    }

    @PostMapping("/user/add")
    public UserEntity addUser(@RequestBody UserEntity userEntity) {
        return this.userService.addNewUser(userEntity);
    }
}
