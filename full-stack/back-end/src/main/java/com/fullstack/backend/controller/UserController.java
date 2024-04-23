package com.fullstack.backend.controller;

import com.fullstack.backend.dto.ApiResponse;
import com.fullstack.backend.model.UserEntity;
import com.fullstack.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
@Slf4j
public class UserController {

  private final UserService userService;

  //  add user
  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addUser(@RequestBody UserEntity userEntity) {
    this.userService.addUser(userEntity);
    log.info("user added");
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Created", new HashSet<>()), HttpStatus.OK);
  }

  //  get user
  @GetMapping("/find")
  public ResponseEntity<ApiResponse> findUser() {
    var response = this.userService.findUser();
    log.info("user found");
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Found SuccessFully", response), HttpStatus.OK);
  }

  //  get user by id
  @GetMapping("/findById/{id}")
  public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
    var response = this.userService.findById(id);
    log.info("user found with id");
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Found SuccessFully", response), HttpStatus.OK);
  }

  //  update the user
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateUser(@RequestBody UserEntity userEntity, @PathVariable Long id) {
    this.userService.updateUser(userEntity, id);
    log.info("user updated");
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Update SuccessFully", new HashSet<>()), HttpStatus.OK);
  }

  //  Delete user
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
    this.userService.deleteUser(id);
    log.info("user deleted");
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Delete Successfully", new HashSet<>()), HttpStatus.OK);
  }
}

