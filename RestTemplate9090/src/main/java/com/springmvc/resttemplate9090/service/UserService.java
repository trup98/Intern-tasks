package com.springmvc.resttemplate9090.service;

import com.springmvc.resttemplate9090.entity.UserEntity;

public interface UserService {
    String findAllUser();

    String findById(Long findById);

    String addNewUser(UserEntity userEntity);

    String updateUserById(Long userId, UserEntity userEntity);

    String deleteUserById(Long deleteId);
}
