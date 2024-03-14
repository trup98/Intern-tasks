package com.springmvc.swaggerui.service;

import com.springmvc.swaggerui.dto.UserRequestDto;
import com.springmvc.swaggerui.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity addUser(UserRequestDto userRequestDto);

    List<UserEntity> findAllUser();

    UserEntity findUserById(Long findbyId);

    UserEntity updateUserById(Long updateId, UserRequestDto userRequestDto);

    void deleteUserById(Long deleteId);
}
