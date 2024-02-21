package com.springboot_jwt.service.impl;

import com.springboot_jwt.dto.RequestDto;
import com.springboot_jwt.entity.UserEntity;
import com.springboot_jwt.enums.CommonEnums;
import com.springboot_jwt.exception.ResourceNotFoundException;
import com.springboot_jwt.repositoty.UserRepository;
import com.springboot_jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserEntity dtoToEntity(RequestDto requestDto){
        return this.modelMapper.map(requestDto,UserEntity.class);
    }



    @Override
    public void addUser(RequestDto requestDto) {
        UserEntity user = dtoToEntity(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        UserEntity userEntity = this.userRepository.save(user);
    }

    @Override
    public List<UserEntity> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public UserEntity findUserById(Long findById) {
        return this.userRepository.findById(findById).orElseThrow(() -> new ResourceNotFoundException(CommonEnums.EXCEPTION_MSG.getMessage() + findById));
    }

    @Override
    public void updateUserById(Long updateId, UserEntity userEntity) {
        UserEntity user = userRepository.findById(updateId).orElseThrow(() -> new ResourceNotFoundException(CommonEnums.EXCEPTION_MSG.getMessage() + updateId));
        user.setName(userEntity.getName());
        user.setEmail(userEntity.getEmail());
        user.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long deleteId) {
        UserEntity user = userRepository.findById(deleteId).orElseThrow(() -> new ResourceNotFoundException(CommonEnums.EXCEPTION_MSG.getMessage() + deleteId));
        this.userRepository.delete(user);
    }
}
