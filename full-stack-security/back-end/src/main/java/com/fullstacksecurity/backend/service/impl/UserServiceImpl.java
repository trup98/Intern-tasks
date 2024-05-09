package com.fullstacksecurity.backend.service.impl;

import com.fullstacksecurity.backend.entity.RoleEntity;
import com.fullstacksecurity.backend.entity.UserDetailsEntity;
import com.fullstacksecurity.backend.entity.UserEntity;
import com.fullstacksecurity.backend.entity.UserRoleMappingEntity;
import com.fullstacksecurity.backend.exception.CustomException;
import com.fullstacksecurity.backend.repository.RoleRepository;
import com.fullstacksecurity.backend.repository.UserDetailsRepository;
import com.fullstacksecurity.backend.repository.UserRepository;
import com.fullstacksecurity.backend.repository.UserRoleMappingRepository;
import com.fullstacksecurity.backend.request.dto.RegisterUserDTO;
import com.fullstacksecurity.backend.response.dto.UserResponseDTO;
import com.fullstacksecurity.backend.service.UserService;
import com.fullstacksecurity.backend.utill.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserDetailsRepository userDetailsRepository;
  private final UserRoleMappingRepository userRoleMappingRepository;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;
  private final Utilities utilities;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void registerUser(RegisterUserDTO registerUserDTO) {
    try {
//      checking user is already
      var user = this.userRepository.findByEmail(registerUserDTO.getEmail());
      if (user.isPresent()) {
        log.info("User Already Exist::", registerUserDTO.getEmail());
        throw new CustomException("User Already Exist", HttpStatus.NOT_ACCEPTABLE);
      }
//      fetch current user

      UserEntity currentUser = utilities.currentUser();

      UserEntity entity = new UserEntity();
      entity.setEmail(registerUserDTO.getEmail());
      entity.setUserName(registerUserDTO.getUserName());
      entity.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
      entity.setCreatedBy(currentUser);
      entity.setUpdatedBy(currentUser);

      UserEntity saveUserEntity = this.userRepository.save(entity);
      log.info("User Saved :::::::::::::::::::::{}", saveUserEntity.getId());

      UserDetailsEntity userDetailsEntity = getUserDetailsEntity(registerUserDTO, saveUserEntity, currentUser);
      this.userDetailsRepository.save(userDetailsEntity);

      List<UserRoleMappingEntity> roleMappingEntityList = new ArrayList<>();

      registerUserDTO.getRoles().forEach(roleId -> {
        RoleEntity roleEntity = this.roleRepository.findById(roleId.longValue()).orElseThrow(() -> new CustomException("Role Not Found" + roleId, HttpStatus.NOT_FOUND));
        UserRoleMappingEntity userRoleMapping = new UserRoleMappingEntity();
        userRoleMapping.setUserId(saveUserEntity);
        userRoleMapping.setRoleId(roleEntity);
        userRoleMapping.setCreatedBy(currentUser);
        userRoleMapping.setUpdatedBy(currentUser);
        roleMappingEntityList.add(userRoleMapping);
      });
      this.userRoleMappingRepository.saveAll(roleMappingEntityList);
      log.info("User Role Mapping saved to database::::{}", roleMappingEntityList);

    } catch (CustomException e) {
      log.info("Exception Catch at Adding User At Database in UserServiceImpl::::{}", HttpStatus.BAD_REQUEST);
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }

  @Override
  public Page<UserResponseDTO> getAll(Pageable pageable, String searchKey) {
    return null;
  }


  private static UserDetailsEntity getUserDetailsEntity(RegisterUserDTO registerUserDTO, UserEntity saveUserEntity, UserEntity currentUser) {
    UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
    userDetailsEntity.setDob(registerUserDTO.getDob());
    userDetailsEntity.setAddress(registerUserDTO.getAddress());
    userDetailsEntity.setFirstName(registerUserDTO.getFirstName());
    userDetailsEntity.setLastName(registerUserDTO.getLastName());
    userDetailsEntity.setGender(registerUserDTO.getGender());
    userDetailsEntity.setUserId(saveUserEntity);
    userDetailsEntity.setCreatedBy(currentUser);
    userDetailsEntity.setUpdatedBy(currentUser);
    return userDetailsEntity;
  }
}
