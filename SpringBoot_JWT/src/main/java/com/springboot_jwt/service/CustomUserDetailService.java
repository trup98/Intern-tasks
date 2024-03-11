package com.springboot_jwt.service;

import com.springboot_jwt.entity.UserEntity;
import com.springboot_jwt.exception.CustomException;
import com.springboot_jwt.repositoty.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByName(username);
        return userEntity.map(CustomUserDetails::new).orElseThrow(() -> new CustomException("User Not Found!", HttpStatus.NOT_FOUND));
    }
}
