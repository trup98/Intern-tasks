package com.springmvc.resttemplatecrud.controller;

import com.springmvc.resttemplatecrud.entity.UserEntity;
import com.springmvc.resttemplatecrud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserEntity> addNewUser(@RequestBody UserEntity userEntity) {
        userEntity = userService.addUser(userEntity);
        return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
    }

    @GetMapping("/findAllUser")
    public List<UserEntity> getAllUser() {
        return this.userService.getAllUser();
    }

    @GetMapping("/findById/{findById}")
    public UserEntity getById(@PathVariable Long findById) {
        return this.userService.findUserById(findById);
    }

    @DeleteMapping("/deleteUser/{deleteUser}")
    public void deleteUser(@PathVariable Long deleteUser) {
        this.userService.deleteUserById(deleteUser);
    }

    @PutMapping("/updateUser/{updateUser}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long updateUser, @RequestBody UserEntity userEntity) {
        userEntity = userService.updateUserById(updateUser, userEntity);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }
}
