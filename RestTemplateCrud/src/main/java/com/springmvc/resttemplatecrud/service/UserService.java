package com.springmvc.resttemplatecrud.service;


import com.springmvc.resttemplatecrud.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    UserEntity addUser(UserEntity userEntity);

    List<UserEntity> getAllUser();

    UserEntity findUserById(Long findById);

    void deleteUserById(Long deleteUser);

    UserEntity updateUserById(Long updateUser, UserEntity userEntity);
}
