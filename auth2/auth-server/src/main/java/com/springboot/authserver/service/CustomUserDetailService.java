package com.springboot.authserver.service;

import com.springboot.authserver.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.springboot.authserver.repository.UserRepository;
import com.springboot.authserver.model.CustomUserDetails;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity =userRepository.findByName(username);
        return userEntity.map(CustomUserDetails::new).orElseThrow(()-> new RuntimeException("User Not Found"));
    }
}
