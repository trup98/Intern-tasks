package com.springmvc.jdbctemplate.repository;

import com.springmvc.jdbctemplate.dto.UserRequestDto;
import com.springmvc.jdbctemplate.model.UserResponseDTO;

import java.util.List;

public interface UserRepository {
    String saveUser(UserRequestDto userRequestDto);

    List<UserResponseDTO> findAll();

    UserResponseDTO findUser(Long findById);

    void deleteUser(Long deleteId);

    int updateUser(UserRequestDto userRequestDto, Long updateId);
}
