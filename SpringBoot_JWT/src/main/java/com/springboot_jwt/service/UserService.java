package com.springboot_jwt.service;

import com.springboot_jwt.dto.UserRequestDto;
import com.springboot_jwt.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> findAll();

    UserResponseDto findUserById(Long id);

    void updateUserById(Long id, UserRequestDto requestDto);

    void deleteUserById(Long id);

    void addUser(UserRequestDto requestDto);
}
