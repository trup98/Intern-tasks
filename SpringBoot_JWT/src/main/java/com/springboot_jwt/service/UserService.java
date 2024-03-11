package com.springboot_jwt.service;

import com.springboot_jwt.dto.UserRequestDto;
import com.springboot_jwt.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> findAll();

    UserResponseDto findUserById(Long findById);

    void updateUserById(Long updateId, UserRequestDto requestDto);

    void deleteUserById(Long deleteId);

    void addUser(UserRequestDto requestDto);
}
