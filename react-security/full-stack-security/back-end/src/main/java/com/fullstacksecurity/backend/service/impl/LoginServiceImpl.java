package com.fullstacksecurity.backend.service.impl;

import com.fullstacksecurity.backend.entity.UserEntity;
import com.fullstacksecurity.backend.entity.UserRoleMappingEntity;
import com.fullstacksecurity.backend.enums.ExceptionEnum;
import com.fullstacksecurity.backend.enums.JwtExceptionEnum;
import com.fullstacksecurity.backend.exception.CustomException;
import com.fullstacksecurity.backend.repository.UserRepository;
import com.fullstacksecurity.backend.repository.UserRoleMappingRepository;
import com.fullstacksecurity.backend.request.dto.LoginRequestDTO;
import com.fullstacksecurity.backend.response.dto.ResponseTokenDTO;
import com.fullstacksecurity.backend.security.jwt.JwtTokenProvider;
import com.fullstacksecurity.backend.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserRoleMappingRepository userRoleMappingRepository;

  @Override
  public ResponseTokenDTO getToken(LoginRequestDTO loginRequestDTO) {

    this.authenticate(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

    var user = this.getUser(loginRequestDTO.getEmail());

    if (this.passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
      return getTokenResponse(user);
    } else {
      throw new CustomException(ExceptionEnum.INCORRECT_USERNAME_OR_PASSWORD.getValue(), HttpStatus.UNAUTHORIZED);
    }
  }

  private UserEntity getUser(String email) {
    return this.userRepository.findByEmail(email).orElseThrow(() -> new CustomException(JwtExceptionEnum.INCORRECT_USERNAME_OR_PASSWORD.getValue(), HttpStatus.UNAUTHORIZED));
  }

  private void authenticate(String email, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    } catch (Exception e) {
      log.error("Exception :: {}", e.getMessage());
      throw new CustomException(JwtExceptionEnum.INCORRECT_USERNAME_OR_PASSWORD.getValue(), HttpStatus.UNAUTHORIZED);
    }
  }

  public ResponseTokenDTO getTokenResponse(UserEntity userEntity) {
    String userRole;
    Optional<UserRoleMappingEntity> userRoleMappingEntity = this.userRoleMappingRepository.findByUserId(userEntity);

    if (userRoleMappingEntity.isPresent()) {
      userRole = userRoleMappingEntity.get().getRoleId().getRole();
      log.info("userRole ::{}", userRole);
    } else {
      throw new CustomException(ExceptionEnum.USER_ROLE_NOT_FOUND.getValue(), HttpStatus.NOT_FOUND);
    }

    try {
      return new ResponseTokenDTO(jwtTokenProvider.createToken(userEntity.getEmail(), userEntity.getId(), userRole), userRole, userEntity.getId(), userEntity.getUserName());
    } catch (Exception e) {
      log.info("Exception Catch In Login Service::::");
      throw new CustomException("Error While Creating Token", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
