package com.springboot_jwt.service;

import com.springboot_jwt.entity.UserEntity;

import java.util.List;

public interface UserService {
    void addUser(UserEntity userEntity);

    List<UserEntity> findAll();

    UserEntity findUserById(Long findById);

    void updateUserById(Long updateId, UserEntity userEntity);

    void deleteUserById(Long deleteId);
}
