package com.springmvc.jdbctemplate.service;

import com.springmvc.jdbctemplate.dto.UserRequestDto;
import com.springmvc.jdbctemplate.model.UserResponseDTO;

import java.util.List;

public interface UserService {
    String addUser(UserRequestDto userRequestDto);

    List<UserResponseDTO> searchAllUser();

    UserResponseDTO findUserById(Long findById);

    void deleteUserById(Long deleteId);

    int updateUserById(UserRequestDto userRequestDto, Long updateId);
}
