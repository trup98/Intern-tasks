package com.springboot_jwt.controller;

import com.springboot_jwt.dto.UserRequestDto;
import com.springboot_jwt.entity.UserEntity;
import com.springboot_jwt.service.UserService;
import com.springboot_jwt.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    //    Add New User;
    @PostMapping("/add")
    public String addUser(@RequestBody UserEntity userEntity) {
        this.userService.addUser(userEntity);
        return "Added User";
    }

    //    Find All User;
    @GetMapping("/search")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserEntity> findAllUser() {
        return this.userService.findAll();
    }

    //    Find User By ID;
    @GetMapping("/findById/{findById}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserEntity findById(@PathVariable Long findById) {
        return this.userService.findUserById(findById);
    }

    //    Update User
    @PutMapping("/update/{updateId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateUser(@PathVariable Long updateId, @RequestBody UserEntity userEntity) {
        this.userService.updateUserById(updateId, userEntity);
        return "Updated";
    }

    //    Delete User
    @DeleteMapping("/delete/{deleteId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteUser(@PathVariable Long deleteId) {
        this.userService.deleteUserById(deleteId);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody UserRequestDto userRequestDto) {
        Authentication authenticate = authenticationManager.
                authenticate(new
                        UsernamePasswordAuthenticationToken(userRequestDto.getUserName(),
                        userRequestDto.getPassword()));


        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(userRequestDto.getUserName());
        } else {
            throw new UsernameNotFoundException("Invalid User Request");
        }

    }
}
