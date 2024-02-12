package com.springboot_jwt.controller;

import com.springboot_jwt.dto.RequestDto;
import com.springboot_jwt.dto.UserRequestDto;
import com.springboot_jwt.entity.UserEntity;
import com.springboot_jwt.service.UserService;
import com.springboot_jwt.utils.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Operation(summary = "Used to Add Data into Database", description =
            "-Name is required " +
                    "- email should contain@  " +
                    "- Password must be 6  " +
                    "- Role should in capital ex:'ROLE_USER'"
    )
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String addUser(@Valid @RequestBody RequestDto requestDto) {
        this.userService.addUser(requestDto);
        return "Added User";
    }

    //    Find All User;
    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @Operation(summary = "Used to Fetch Data From Database")
    public List<UserEntity> findAllUser() {
        return this.userService.findAll();
    }

    //    Find User By ID;
    @GetMapping("/findById/{findById}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @Operation(summary = "Used to Fetch Data From Database By ID")
    public UserEntity findById(@PathVariable Long findById) {
        return this.userService.findUserById(findById);
    }

    //    Update User
    @PutMapping("/update/{updateId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "Used to Update Data into Database", description =
            "-Name is required " +
                    "- email should contain@  " +
                    "- Password must be 6  " +
                    "- Role should in capital ex:'ROLE_USER'"
    )
    public String updateUser(@Valid @PathVariable Long updateId, @RequestBody UserEntity userEntity) {
        this.userService.updateUserById(updateId, userEntity);
        return "Updated";
    }

    //    Delete User
    @DeleteMapping("/delete/{deleteId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "Used to Delete Data From Database")
    public void deleteUser(@PathVariable Long deleteId) {
        this.userService.deleteUserById(deleteId);
    }

    @PostMapping("/authenticate")
    @Operation(summary = "For Generating Token",description = "for Admin userName:trup  " +
            "  password:0000  "+
            "  for User userName:user  "+
            "  password:1111")
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
