package com.springmvc.jdbctemplate.service.impl;

import com.springmvc.jdbctemplate.dto.UserRequestDto;
import com.springmvc.jdbctemplate.model.UserResponseDTO;
import com.springmvc.jdbctemplate.repository.UserRepository;
import com.springmvc.jdbctemplate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String addUser(UserRequestDto userRequestDto) {
        return this.userRepository.saveUser(userRequestDto);
    }

    @Override
    public List<UserResponseDTO> searchAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public UserResponseDTO findUserById(Long findById) {
        return this.userRepository.findUser(findById);
    }

    @Override
    public void deleteUserById(Long deleteId) {
        this.userRepository.deleteUser(deleteId);
    }

    @Override
    public int updateUserById(UserRequestDto userRequestDto, Long updateId) {
        return this.userRepository.updateUser(userRequestDto, updateId);
    }
}
