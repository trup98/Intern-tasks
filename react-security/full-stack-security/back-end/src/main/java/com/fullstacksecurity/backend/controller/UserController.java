package com.fullstacksecurity.backend.controller;

import com.fullstacksecurity.backend.enums.GetSortBy;
import com.fullstacksecurity.backend.request.dto.RegisterUserDTO;
import com.fullstacksecurity.backend.request.dto.UpdateUserDTO;
import com.fullstacksecurity.backend.response.dto.ApiResponse;
import com.fullstacksecurity.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;


  @PostMapping("/addUser")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
    this.userService.registerUser(registerUserDTO);
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Registered Successfully", new HashMap<>()), HttpStatus.OK);
  }

  @GetMapping("/getAll")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public ResponseEntity<ApiResponse> getAll(@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo, @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize, @RequestParam(value = "sortBy", required = false, defaultValue = "ID") GetSortBy getSortBy, @RequestParam(value = "sortOrder", required = false, defaultValue = "ASC") Sort.Direction sortOrder, @RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey) {
    Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortOrder, getSortBy.getValue()));
    var response = this.userService.getAll(pageable, searchKey);
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Found Successfully", response), HttpStatus.OK);
  }

  @GetMapping("/getUserById/{id}")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public ResponseEntity<ApiResponse> getUserById(@PathVariable(value = "id") Long id) {
    var response = this.userService.getUserById(id);
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Found Successfully", response), HttpStatus.OK);
  }

  @PatchMapping("/changeStatus/{id}/{status}")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
  public ResponseEntity<ApiResponse> changeTheStatus(@PathVariable(value = "id") Long id, @PathVariable(value = "status") Boolean status) {
    this.userService.changeStatus(id, status);
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Status Changed", new HashMap<>()), HttpStatus.OK);
  }

  @PutMapping("/updateUser/{id}")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public ResponseEntity<ApiResponse> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
    this.userService.updateUser(id, updateUserDTO);
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Updated Successfully", new HashMap<>()), HttpStatus.OK);
  }

  @DeleteMapping("deleteUser/{id}")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Long id){
    this.userService.deleteUser(id);
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Deleted Successfully", new HashMap<>()), HttpStatus.OK);
  }
}
