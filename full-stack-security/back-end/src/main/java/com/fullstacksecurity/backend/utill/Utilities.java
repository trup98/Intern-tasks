package com.fullstacksecurity.backend.utill;

import com.fullstacksecurity.backend.entity.UserEntity;
import com.fullstacksecurity.backend.repository.UserRepository;
import com.fullstacksecurity.backend.response.dto.TokenClaims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class Utilities {

  private final UserRepository userRepository;
  private final TokenClaims tokenClaims;

  /*<>flatMap used instead of map bcz userRepository.findById method already return Optional<UserEntity> so to prevent nested Optional object <>
   * */
  public UserEntity currentUser() {
    return Optional.ofNullable(this.tokenClaims.getUserId())
      .flatMap(this.userRepository::findById)
      .orElse(null);
  }

}
