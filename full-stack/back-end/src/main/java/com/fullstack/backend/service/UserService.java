package com.fullstack.backend.service;

import com.fullstack.backend.model.UserEntity;

import java.util.List;

public interface UserService {
  void addUser(UserEntity userEntity);

  List<UserEntity> findUser();
}
