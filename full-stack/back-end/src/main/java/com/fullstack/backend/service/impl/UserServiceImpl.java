package com.fullstack.backend.service.impl;

import com.fullstack.backend.exception.CustomException;
import com.fullstack.backend.model.UserEntity;
import com.fullstack.backend.repository.UserRepository;
import com.fullstack.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public void addUser(UserEntity userEntity) {
    try {
      this.userRepository.save(userEntity);
    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }

  @Override
  public List<UserEntity> findUser() {
    try {
      return this.userRepository.findAll();
    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }
}
