package com.fullstack.backend.service;

import com.fullstack.backend.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
  void addUser(UserEntity userEntity);

  List<UserEntity> findUser();

  Optional<UserEntity> findById(Long id);

  void updateUser(UserEntity userEntity, Long id);

  void deleteUser(Long id);
}
