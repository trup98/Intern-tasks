package com.springboot_jwt.service.impl;

import com.springboot_jwt.dto.UserRequestDto;
import com.springboot_jwt.dto.UserResponseDto;
import com.springboot_jwt.entity.UserEntity;
import com.springboot_jwt.exception.CustomException;
import com.springboot_jwt.repositoty.UserRepository;
import com.springboot_jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserResponseDto mapEntityToDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserResponseDto.class);
    }

    @Override
    public void addUser(UserRequestDto requestDto) {
        try {
            var user = this.userRepository.findByEmail(requestDto.getEmail());
            if (user.isPresent()) {
                throw new CustomException("User Exist", HttpStatus.NOT_ACCEPTABLE);
            }
            UserEntity userEntity = new UserEntity();
            userEntity.setName(requestDto.getName());
            userEntity.setEmail(requestDto.getEmail());
            userEntity.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            userEntity.setRoles(requestDto.getRoles());
            this.userRepository.save(userEntity);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }

    @Override
    public List<UserResponseDto> findAll() {
        try {
            return this.userRepository.findAll().stream()
                    .map(this::mapEntityToDto).collect(Collectors.toList());
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }

    @Override
    public UserResponseDto findUserById(Long findById) {
        try {
            var user = this.userRepository.findById(findById);
            if (user.isEmpty()) {
                throw new CustomException("User Not Found", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(user, UserResponseDto.class);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }

    @Override
    public void updateUserById(Long updateId, UserRequestDto requestDto) {
        try {
            var user = this.userRepository.findById(updateId);
            if (user.isEmpty()) {
                throw new CustomException("User Not Found!", HttpStatus.NOT_FOUND);
            }
            UserEntity userEntity = user.get();
            userEntity.setName(requestDto.getName());
            userEntity.setEmail(requestDto.getEmail());
            userEntity.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            userEntity.setRoles(requestDto.getRoles());
            this.userRepository.save(userEntity);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }

    }

    @Override
    public void deleteUserById(Long deleteId) {
        try {
            var user = userRepository.findById(deleteId);
            if (user.isEmpty()) {
                throw new CustomException("User Not Found", HttpStatus.NOT_FOUND);
            }
            this.userRepository.deleteById(deleteId);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }
}
