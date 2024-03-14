package com.springmvc.basic_spring_security.service;

import com.springmvc.basic_spring_security.model.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUser();

    UserEntity getById(String userName);

    UserEntity addNewUser(UserEntity userEntity);
}
