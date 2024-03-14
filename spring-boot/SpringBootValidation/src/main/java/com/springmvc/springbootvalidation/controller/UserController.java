package com.springmvc.springbootvalidation.controller;

import com.springmvc.springbootvalidation.model.UserEntity;
import com.springmvc.springbootvalidation.reques.dto.UserRequestDto;
import com.springmvc.springbootvalidation.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    //  Add New User
    @PostMapping("/add")
    public UserEntity addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return this.userService.addNewUser(userRequestDto);
    }
    //  Find All User
    @GetMapping("/findAll")
    public ResponseEntity<List<UserEntity>> search() {
        List<UserEntity> userEntityList = this.userService.findAll();
        return ResponseEntity.of(Optional.of(userEntityList));
    }
    //    Find User By ID
    @GetMapping("/findById/id/{findId}")
    public Optional<UserEntity> getUserById(@PathVariable Long findId) {
        return this.userService.getById(findId);
    }
    //  Delete User
    @DeleteMapping("/deleteId/id/{deleteid}")
    public String deleteUser(@PathVariable Long deleteid) {
        return this.userService.deleteUserId(deleteid);
    }
    //    Update User By ID
    @PutMapping("/updateId/id/{updateId}")
    public String updateUserById(@Valid @RequestBody UserRequestDto userRequestDto, @PathVariable Long updateId) {
        return this.userService.updateUser(updateId, userRequestDto);
    }
}
