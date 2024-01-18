package com.springmvc.springbootvalidation.service.impl;

import com.springmvc.springbootvalidation.model.UserEntity;
import com.springmvc.springbootvalidation.repository.UserRepository;
import com.springmvc.springbootvalidation.reques.dto.UserRequestDto;
import com.springmvc.springbootvalidation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity addNewUser(UserRequestDto userRequestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userRequestDto.getFirstName());
        userEntity.setLastName(userRequestDto.getLastName());
        userEntity.setEmail(userRequestDto.getEmail());
        userEntity.setPassword(userRequestDto.getPassword());
        return this.userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getById(Long findId) {
        return this.userRepository.findById(findId);
    }

    @Override
    public String deleteUserId(Long deleteid) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(deleteid);
        this.userRepository.deleteById(deleteid);
        return "Deleted !";
    }

    @Override
    public String updateUser(Long updateId, UserRequestDto userRequestDto) {
        UserEntity userEntity = userRepository.findById(updateId).orElseThrow(() -> new RuntimeException("Can't find Data"));
        userEntity.setFirstName(userRequestDto.getFirstName());
        userEntity.setLastName(userRequestDto.getLastName());
        userEntity.setEmail(userRequestDto.getEmail());
        userEntity.setPassword(userRequestDto.getPassword());
        this.userRepository.save(userEntity);
        return "Updated !";
    }
}
