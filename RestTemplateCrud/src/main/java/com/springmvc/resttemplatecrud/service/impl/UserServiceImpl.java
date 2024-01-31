package com.springmvc.resttemplatecrud.service.impl;

import com.springmvc.resttemplatecrud.entity.UserEntity;
import com.springmvc.resttemplatecrud.repository.UserRepository;
import com.springmvc.resttemplatecrud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public UserEntity findUserById(Long findById) {
        return this.userRepository.findById(findById).orElseThrow(()->new RuntimeException("Cannot Find User: "+findById));
    }

    @Override
    public void deleteUserById(Long deleteUser) {
        UserEntity userEntity = userRepository.findById(deleteUser).orElseThrow(()->new RuntimeException("Cannot Find User: "+deleteUser));
        userRepository.delete(userEntity);
    }

    @Override
    public UserEntity updateUserById(Long updateUser, UserEntity userEntity) {
        UserEntity entity = userRepository.findById(updateUser).orElseThrow(()->new RuntimeException("Cannot Find User: "+updateUser));
        entity.setFirstName(userEntity.getFirstName());
        entity.setLastName(userEntity.getLastName());
        entity.setMobileNo(userEntity.getMobileNo());
        entity.setAddress(userEntity.getAddress());
        return this.userRepository.save(entity);
    }
}
