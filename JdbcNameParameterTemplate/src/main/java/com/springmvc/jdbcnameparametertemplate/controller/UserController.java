package com.springmvc.jdbcnameparametertemplate.controller;

import com.springmvc.jdbcnameparametertemplate.service.UserService;
import com.springmvc.jdbcnameparametertemplate.dto.UserRequestDto;
import com.springmvc.jdbcnameparametertemplate.model.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/addUser")
    public int addNewUser(@RequestBody UserRequestDto userRequestDto) {
        return this.userService.addUser(userRequestDto);
    }

    @GetMapping("/search")
    public List<UserResponseDTO> findAll() {
        return this.userService.searchAllUser();
    }

    @GetMapping("/findById/{findById}")
    public UserResponseDTO findUser(@PathVariable Long findById) {
        return this.userService.findUserById(findById);
    }

    @DeleteMapping("/delete/{deleteId}")
    public void deleteUser(@PathVariable Long deleteId) {
        this.userService.deleteUserById(deleteId);
    }

    @PutMapping("/update/{updateId}")
    public int updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable Long updateId) {
        return this.userService.updateUserById(userRequestDto, updateId);
    }
}
