package com.fullstack.backend.controller;

import com.fullstack.backend.dto.ApiResponse;
import com.fullstack.backend.model.UserEntity;
import com.fullstack.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UserController {

  private final UserService userService;

  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addUser(@RequestBody UserEntity userEntity) {
    this.userService.addUser(userEntity);
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Created", new HashSet<>()), HttpStatus.OK);
  }

  @GetMapping("find")
  public ResponseEntity<ApiResponse> findUser() {
    var response = this.userService.findUser();
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Found SuccessFully", response), HttpStatus.OK);
  }
}

