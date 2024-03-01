package com.springprofiling.springprofiling.controller;

import com.springprofiling.springprofiling.dto.ApiResponse;
import com.springprofiling.springprofiling.dto.UserRequestDto;
import com.springprofiling.springprofiling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //    add User
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addUser(@RequestBody UserRequestDto userRequestDto) {
        this.userService.addUser(userRequestDto);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Added SuccessFully", new HashMap<>()), HttpStatus.OK);
    }

    //    find All
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> findAll() {
        var response = this.userService.findAll();
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Found", response), HttpStatus.OK);
    }

    //    find by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findUserById(@PathVariable(value = "id") Long id) {
        var response = this.userService.findUserById(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Found", response), HttpStatus.OK);
    }

    //    update User
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable(value = "id") Long id, @RequestBody UserRequestDto userRequestDto) {
        this.userService.updateUser(id, userRequestDto);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Updated SuccessFully", new HashMap<>()), HttpStatus.OK);
    }

    //    delete User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "id") Long id) {
        this.userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Deleted", new HashMap<>()), HttpStatus.OK);
    }
}
