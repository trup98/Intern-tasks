package com.springboot_jwt.controller;

import com.springboot_jwt.dto.*;
import com.springboot_jwt.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final UserService userService;

    /*Add New User*/
    @PostMapping("/add")
    @Operation(summary = "Used to Add Data into Database", description =
            "-Name is required " +
                    "- email should contain@  " +
                    "- Password must be 6  " +
                    "- Role should in capital ex:'ROLE_USER'"
    )
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody UserRequestDto requestDto) {
        this.userService.addUser(requestDto);
        log.info("User Saved!!!!");
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Created!", new HashSet<>()), HttpStatus.OK);
    }

    /*Find All User*/
    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @Operation(summary = "Used to Fetch Data From Database")
    public ResponseEntity<ApiResponse> findAllUser() {
        var response = this.userService.findAll();
        log.info("User Found!!!!");
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Found!", response), HttpStatus.OK);
    }

    /*Find User By ID*/
    @GetMapping("/findById/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @Operation(summary = "Used to Fetch Data From Database By ID")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
        var response = this.userService.findUserById(id);
        log.info("User Found!!!!");
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Found!", response), HttpStatus.OK);
    }

    /*Update User*/
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "Used to Update Data into Database", description =
            "-Name is required " +
                    "- email should contain@  " +
                    "- Password must be 6  " +
                    "- Role should in capital ex:'ROLE_USER'"
    )
    public ResponseEntity<ApiResponse> updateUser(@Valid @PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        this.userService.updateUserById(id, requestDto);
        log.info("User Updated!!!");
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Updated!", new HashSet<>()), HttpStatus.OK);
    }

    /*Delete User*/
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "Used to Delete Data From Database")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        this.userService.deleteUserById(id);
        log.info("User Deleted!!!!");
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User Deleted!", new HashSet<>()), HttpStatus.OK);
    }


}
