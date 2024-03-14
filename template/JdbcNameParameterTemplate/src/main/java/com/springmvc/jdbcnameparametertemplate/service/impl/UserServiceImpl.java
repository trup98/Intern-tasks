package com.springmvc.jdbcnameparametertemplate.service.impl;

import com.springmvc.jdbcnameparametertemplate.dto.UserRequestDto;
import com.springmvc.jdbcnameparametertemplate.model.UserResponseDTO;
import com.springmvc.jdbcnameparametertemplate.repository.UserRepository;
import com.springmvc.jdbcnameparametertemplate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public int addUser(UserRequestDto userRequestDto) {
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
