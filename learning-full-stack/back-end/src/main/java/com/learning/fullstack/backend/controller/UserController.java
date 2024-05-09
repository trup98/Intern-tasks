package com.learning.fullstack.backend.controller;

import com.learning.fullstack.backend.dto.ApiResponse;
import com.learning.fullstack.backend.dto.UserRequestDTO;
import com.learning.fullstack.backend.enums.GetSortBy;
import com.learning.fullstack.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/api/user")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UserController {

  private final UserService userService;


  @PostMapping(value = "/save")
  public ResponseEntity<ApiResponse> saveUser(@RequestBody UserRequestDTO userRequestDTO) {
    this.userService.saveUser(userRequestDTO);
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Created!", new HashSet<>()), HttpStatus.OK);

  }

  @GetMapping(value = "/find")
  public ResponseEntity<ApiResponse> findUser(@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                                              @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                              @RequestParam(value = "sortBy", defaultValue = "ID", required = false) GetSortBy getSortBy,
                                              @RequestParam(value = "sortOrder", defaultValue = "ASC", required = false) Sort.Direction sortOrder,
                                              @RequestParam(value = "searchKey", defaultValue = "", required = false) String searchKey) {
    Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortOrder, getSortBy.getValue()));
    var response = this.userService.findUser(pageable, searchKey);
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User found!", response), HttpStatus.OK);
  }

  @GetMapping(value = "/findById/{id}")
  public ResponseEntity<ApiResponse> findUserById(@PathVariable Long id) {
    var response = this.userService.findById(id);
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User found!", response), HttpStatus.OK);

  }

  @PutMapping(value = "/update/{id}")
  public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
    this.userService.updateUser(id, userRequestDTO);
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Updated!", new HashSet<>()), HttpStatus.OK);

  }

  @DeleteMapping(value = "/delete/{id}")
  public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
    this.userService.deleteUser(id);
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Deleted!", new HashSet<>()), HttpStatus.OK);
  }

  @PatchMapping("/changeStatus/{id}/{status}")
  public ResponseEntity<ApiResponse> changeStatus(@PathVariable Long id, @PathVariable Boolean status) {
    this.userService.changeStatus(id, status);
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Status Changed!", new HashSet<>()), HttpStatus.OK);
  }
}
