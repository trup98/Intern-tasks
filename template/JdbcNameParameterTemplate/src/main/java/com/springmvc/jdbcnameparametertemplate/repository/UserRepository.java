package com.springmvc.jdbcnameparametertemplate.repository;

import com.springmvc.jdbcnameparametertemplate.dto.UserRequestDto;
import com.springmvc.jdbcnameparametertemplate.model.UserResponseDTO;

import java.util.List;

public interface UserRepository {
    int saveUser(UserRequestDto userRequestDto);

    List<UserResponseDTO> findAll();

    UserResponseDTO findUser(Long findById);

    void deleteUser(Long deleteId);

    int updateUser(UserRequestDto userRequestDto, Long updateId);
}
