package com.springmvc.swaggerui.controller;

import com.springmvc.swaggerui.dto.UserRequestDto;
import com.springmvc.swaggerui.entity.UserEntity;
import com.springmvc.swaggerui.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User Controller", description = "Contain Basic Crud Api")
public class UserController {
    private final UserService userService;

    //    Add Data
    @PostMapping("/add")
    @Operation(summary = "Used to Add Data into Database", description = "ADD NEW DATA")
    public UserEntity addNewUser(@RequestBody UserRequestDto userRequestDto) {
        return this.userService.addUser(userRequestDto);
    }

    //  Get All Data
    @GetMapping("/search")
    @Operation(summary = "Method used to get all Data from Database", description = "FETCH ALL DATA")
    public List<UserEntity> searchAll() {
        return this.userService.findAllUser();
    }

    //  Get Particular Data
    @GetMapping("/findById/{findbyId}")
    @Operation(summary = "Method used to get particular user Data from Database", description = "FETCH DATA BY PARTICULAR ID")
    public UserEntity findById(@Parameter(description = "Give Id to find a User") @PathVariable Long findbyId) {
        return this.userService.findUserById(findbyId);
    }

    //  Update Particular User
    @PutMapping("/update/{updateId}")
    @Operation(summary = "Method used to Update particular Data from Database", description = "UPDATE USER")
    public UserEntity updateUser(@PathVariable Long updateId, @RequestBody UserRequestDto userRequestDto) {
        return this.userService.updateUserById(updateId, userRequestDto);
    }

    //  Delete Particular User
    @DeleteMapping("/delete/{deleteId}")
    @Operation(summary = "Method used to delete particular Data from Database", description = "DELETE USER")
    public void deleteUser(@Parameter(description = "Give Id to delete the User") @PathVariable Long deleteId) {
        this.userService.deleteUserById(deleteId);
    }
}
