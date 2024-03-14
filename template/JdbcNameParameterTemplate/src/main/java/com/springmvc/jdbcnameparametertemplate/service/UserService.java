package com.springmvc.jdbcnameparametertemplate.service;

import com.springmvc.jdbcnameparametertemplate.dto.UserRequestDto;
import com.springmvc.jdbcnameparametertemplate.model.UserResponseDTO;

import java.util.List;

public interface UserService {
    int addUser(UserRequestDto userRequestDto);

    List<UserResponseDTO> searchAllUser();

    UserResponseDTO findUserById(Long findById);

    void deleteUserById(Long deleteId);

    int updateUserById(UserRequestDto userRequestDto, Long updateId);
}
