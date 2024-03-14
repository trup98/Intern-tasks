package com.springprofiling.springprofiling.service.impl;

import com.springprofiling.springprofiling.dto.UserRequestDto;
import com.springprofiling.springprofiling.dto.UserResponseDto;
import com.springprofiling.springprofiling.entity.UserEntity;
import com.springprofiling.springprofiling.exception.CustomException;
import com.springprofiling.springprofiling.repository.UserRepository;
import com.springprofiling.springprofiling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Profile(value = {"local", "dev", "prod"})
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserResponseDto mapDtoToEntity(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserResponseDto.class);
    }

    @Override
    public void addUser(UserRequestDto userRequestDto) {

        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(userRequestDto.getUserName());
            userEntity.setEmail(userRequestDto.getEmail());
            userEntity.setMobileNo(userEntity.getMobileNo());
            userEntity.setPassword(userRequestDto.getPassword());
            this.userRepository.save(userEntity);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }

    }

    @Override
    public List<UserResponseDto> findAll() {
        try {
            return this.userRepository.findAll().
                    stream().map(this::mapDtoToEntity).toList();
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        try {
            var user = this.userRepository.findById(id);
            if (user.isEmpty()) {
                throw new CustomException("User Not Found: " + id, HttpStatus.NOT_FOUND);
            }
            return this.modelMapper.map(user.get(), UserResponseDto.class);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }

    @Override
    public void updateUser(Long id, UserRequestDto userRequestDto) {
        try {
            var user = this.userRepository.findById(id);
            if (user.isEmpty()) {
                throw new CustomException("User Not Found: " + id, HttpStatus.NOT_FOUND);
            }
            UserEntity userEntity = user.get();
            userEntity.setUserName(userRequestDto.getUserName());
            userEntity.setEmail(userRequestDto.getEmail());
            userEntity.setMobileNo(userRequestDto.getMobileNo());
            userEntity.setPassword(userRequestDto.getPassword());
            this.userRepository.save(userEntity);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            var user = userRepository.findById(id);
            if (user.isEmpty()) {
                throw new CustomException("User Not Found", HttpStatus.NOT_FOUND);
            }
            this.userRepository.deleteById(id);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }
}
