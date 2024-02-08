package com.springboot_jwt.service.impl;

import com.springboot_jwt.entity.UserEntity;
import com.springboot_jwt.repositoty.UserRepository;
import com.springboot_jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void addUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        this.userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public UserEntity findUserById(Long findById) {
        return this.userRepository.findById(findById).orElseThrow(() -> new RuntimeException("Can't Find User By ID " + findById));
    }

    @Override
    public void updateUserById(Long updateId, UserEntity userEntity) {
        UserEntity user = userRepository.findById(updateId).orElseThrow(() -> new RuntimeException("Can't Find User By ID " + updateId));
        user.setName(userEntity.getName());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        this.userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long deleteId) {
        this.userRepository.deleteById(deleteId);
    }
}
