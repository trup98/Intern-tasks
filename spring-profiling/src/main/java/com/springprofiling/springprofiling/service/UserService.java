package com.springprofiling.springprofiling.service;

import com.springprofiling.springprofiling.dto.UserRequestDto;
import com.springprofiling.springprofiling.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    void addUser(UserRequestDto userRequestDto);

    List<UserResponseDto> findAll();

    UserResponseDto findUserById(Long id);

    void updateUser(Long id, UserRequestDto userRequestDto);

    void deleteUser(Long id);
}
