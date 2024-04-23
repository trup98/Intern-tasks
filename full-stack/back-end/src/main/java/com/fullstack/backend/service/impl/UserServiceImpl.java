package com.fullstack.backend.service.impl;

import com.fullstack.backend.exception.CustomException;
import com.fullstack.backend.model.UserEntity;
import com.fullstack.backend.repository.UserRepository;
import com.fullstack.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

  @Override
  public Optional<UserEntity> findById(Long id) {
    try {
      var user = this.userRepository.findById(id);
      if (user.isEmpty()) {
        throw new CustomException("User Not Found", HttpStatus.NOT_FOUND);
      }
      return this.userRepository.findById(id);
    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }

  @Override
  public void updateUser(UserEntity userEntity, Long id) {
    try {
      var user = this.userRepository.findById(id);
      if (user.isEmpty()) {
        throw new CustomException("User Not Found", HttpStatus.NOT_FOUND);
      }
      UserEntity entity = user.get();
      entity.setName(userEntity.getName());
      entity.setEmail(userEntity.getEmail());
      entity.setUserName(userEntity.getUserName());
      this.userRepository.save(entity);
    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }

  @Override
  public void deleteUser(Long id) {
    try {
      var user = this.userRepository.findById(id);
      if (user.isEmpty()) {
        throw new CustomException("User Not Found", HttpStatus.NOT_FOUND);
      }
      this.userRepository.deleteById(id);
    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }
}
