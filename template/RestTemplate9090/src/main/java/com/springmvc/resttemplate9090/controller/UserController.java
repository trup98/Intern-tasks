package com.springmvc.resttemplate9090.controller;

import com.springmvc.resttemplate9090.entity.UserEntity;
import com.springmvc.resttemplate9090.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/template")
public class UserController {
    private final UserService userService;

    @GetMapping("/user/findAllUser")
    public String getAllUser() {
        return this.userService.findAllUser();
    }

    @GetMapping("/user/findById/{findById}")
    public String getById(@PathVariable Long findById) {
        return this.userService.findById(findById);
    }

    @PostMapping("/user/addUser")
    public String addUser(@RequestBody UserEntity userEntity) {
        return this.userService.addNewUser(userEntity);
    }

    @PutMapping("/user/updateUser/{userId}")
    public String updateUser(@PathVariable Long userId, @RequestBody UserEntity userEntity) {
        return this.userService.updateUserById(userId, userEntity);
    }

    @DeleteMapping("/user/delete/{deleteId}")
    public String deleteUser(@PathVariable Long deleteId) {
        return this.userService.deleteUserById(deleteId);
    }
}
