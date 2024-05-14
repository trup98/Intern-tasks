package com.fullstacksecurity.backend.service.impl;

import com.fullstacksecurity.backend.entity.UserEntity;
import com.fullstacksecurity.backend.entity.UserRoleMappingEntity;
import com.fullstacksecurity.backend.enums.ExceptionEnum;
import com.fullstacksecurity.backend.repository.UserRepository;
import com.fullstacksecurity.backend.repository.UserRoleMappingRepository;
import com.fullstacksecurity.backend.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

  private final UserRepository userRepository;
  private final UserRoleMappingRepository userRoleMappingRepository;

  @Override
  public Optional<UserDetails> findUserByEmail(String userName) {
    UserEntity userEntity = this.userRepository.findByEmail(userName).orElseThrow(() -> new RuntimeException(ExceptionEnum.USER_NOT_FOUND.getValue()));

    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    UserRoleMappingEntity userRoleMappingEntity = this.userRoleMappingRepository.findByUserId(userEntity).orElseThrow(() -> new RuntimeException(ExceptionEnum.USER_NOT_FOUND.getValue()));

    grantedAuthorities.add(new SimpleGrantedAuthority(userRoleMappingEntity.getRoleId().getRole()));

    return Optional.of(new User(userEntity.getUserName(), userEntity.getPassword(), grantedAuthorities));
  }
}
