package com.springmvc.swaggerui.service.impl;

import com.springmvc.swaggerui.dto.UserRequestDto;
import com.springmvc.swaggerui.entity.UserEntity;
import com.springmvc.swaggerui.repository.*;
import com.springmvc.swaggerui.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserEntity addUser(UserRequestDto userRequestDto) {
        UserEntity userEntity =new UserEntity();
        userEntity.setUserName(userRequestDto.getUserName());
        userEntity.setLastName(userRequestDto.getLastName());
        userEntity.setEmail(userRequestDto.getEmail());
        return this.userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> findAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public UserEntity findUserById(Long findbyId) {
        return this.userRepository.findById(findbyId).orElseThrow(()-> new RuntimeException("Not Found"));
    }

    @Override
    public UserEntity updateUserById(Long updateId, UserRequestDto userRequestDto) {
        UserEntity user = userRepository.findById(updateId).orElseThrow(()-> new RuntimeException("Not Found User"+updateId));
        user.setUserName(userRequestDto.getUserName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long deleteId) {
        this.userRepository.deleteById(deleteId);
    }
}
