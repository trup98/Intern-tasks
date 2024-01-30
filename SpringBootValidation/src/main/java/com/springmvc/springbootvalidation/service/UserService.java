package com.springmvc.springbootvalidation.service;

import com.springmvc.springbootvalidation.model.UserEntity;
import com.springmvc.springbootvalidation.reques.dto.UserRequestDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity addNewUser(UserRequestDto userRequestDto);
    List<UserEntity> findAll();
    Optional<UserEntity> getById(Long findId);
    String deleteUserId(Long deleteid);
    String updateUser(Long updateId, UserRequestDto userRequestDto);
}
