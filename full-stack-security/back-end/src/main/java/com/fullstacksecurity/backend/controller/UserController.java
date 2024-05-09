package com.fullstacksecurity.backend.controller;

import com.fullstacksecurity.backend.enums.GetSortBy;
import com.fullstacksecurity.backend.request.dto.RegisterUserDTO;
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

//  @GetMapping("/getAll")
//  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
//  public ResponseEntity<ApiResponse> getAll(@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
//                                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
//                                            @RequestParam(value = "sortBy", required = false, defaultValue = "ID") GetSortBy getSortBy,
//                                            @RequestParam(value = "sortOrder", required = false, defaultValue = "ASC") Sort.Direction sortOrder,
//                                            @RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey) {
//    Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortOrder,getSortBy.getValue()));
//    var response = this.userService.getAll(pageable,searchKey);
//    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Found Successfully", response), HttpStatus.OK);
//  }
}
