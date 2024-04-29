package com.learning.fullstack.backend.service;

import com.learning.fullstack.backend.dto.UserRequestDTO;
import com.learning.fullstack.backend.dto.UserResponseDTO;
import com.learning.fullstack.backend.entity.HobbyEntity;
import com.learning.fullstack.backend.entity.UserEntity;
import com.learning.fullstack.backend.entity.UserHobbyMappingEntity;
import com.learning.fullstack.backend.exception.CustomException;
import com.learning.fullstack.backend.projection.GetAllUserProjection;
import com.learning.fullstack.backend.repository.HobbyRepository;
import com.learning.fullstack.backend.repository.UserHobbyMappingRepository;
import com.learning.fullstack.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserHobbyMappingRepository userHobbyMappingRepository;
  private final HobbyRepository hobbyRepository;


  @Override
  @Transactional(rollbackFor = Exception.class)
  public void saveUser(UserRequestDTO userRequestDTO) {

    try {
      // check user exists
      var user = userRepository.findByEmail(userRequestDTO.getEmail());
      if (user.isPresent()) {
        throw new CustomException("User Already Exist With this Email !!", HttpStatus.NOT_ACCEPTABLE);
      }
      // get user entity
      UserEntity userEntity = UserEntity.builder()
        .userName(userRequestDTO.getUserName())
        .firstName(userRequestDTO.getFirstName())
        .lastName(userRequestDTO.getLastName())
        .email(userRequestDTO.getEmail())
        .gender(userRequestDTO.getGender())
        .address(userRequestDTO.getAddress())
        .dob(userRequestDTO.getDob())
        .build();

      // save user
      UserEntity savedUser = this.userRepository.save(userEntity);
      List<UserHobbyMappingEntity> userHobbyMappingEntities = new ArrayList<>();

      // get user hobby mapping entities

      userRequestDTO.getHobbyIdList().forEach(hobbyId -> {
        // fetch by id  and push to list
        HobbyEntity hobbyEntity = this.hobbyRepository.findById(Long.valueOf(hobbyId)).orElseThrow(() -> new CustomException("Hobby Not Found: " + hobbyId, HttpStatus.NOT_FOUND));

        UserHobbyMappingEntity userHobbyMappingEntity = new UserHobbyMappingEntity();
        userHobbyMappingEntity.setUserId(savedUser);
        userHobbyMappingEntity.setHobbyId(hobbyEntity);
        userHobbyMappingEntities.add(userHobbyMappingEntity);
      });

      // save user hobby mappings
      this.userHobbyMappingRepository.saveAll(userHobbyMappingEntities);
    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }


  }

  @Override
  public Page<GetAllUserProjection> findUser(Pageable pageable, String searchKey) {
    try {
      Page<GetAllUserProjection> getAllUserProjections = this.userRepository.getAllUser(pageable, searchKey);
      if (getAllUserProjections.isEmpty()) {
        log.info("Exception Catch:::::::::::::::::::::::");
        throw new CustomException("Users NOT Found", HttpStatus.NOT_FOUND);
      }
      return getAllUserProjections;
    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }

  @Override
  public UserResponseDTO findById(Long id) {

    try {
      var optionalUser = this.userRepository.findByIdAndIsActive(id);

      if (optionalUser.isEmpty()) {
        log.info("Exception Catch::::");
        throw new CustomException("User Not Found with ID: " + id, HttpStatus.NOT_FOUND);
      }


      UserEntity user = userRepository.findById(id).orElseThrow(() -> new CustomException("User Not Found", HttpStatus.NOT_FOUND));

      List<UserHobbyMappingEntity> userHobbyMappingEntityList = userHobbyMappingRepository.findByUserId(user);

      List<UserResponseDTO.HobbyResponseDTO> hobbyResponseDTOS = new ArrayList<>();

      for (UserHobbyMappingEntity UserResponseDTO : userHobbyMappingEntityList) {
        HobbyEntity hobbyEntity = UserResponseDTO.getHobbyId();
        com.learning.fullstack.backend.dto.UserResponseDTO.HobbyResponseDTO hobbyResponseDTO = new UserResponseDTO.HobbyResponseDTO(hobbyEntity.getName());
        hobbyResponseDTOS.add(hobbyResponseDTO);
      }

      UserResponseDTO UserResponseDTO = new UserResponseDTO();
      UserResponseDTO.setId(user.getId());
      UserResponseDTO.setUserName(user.getUserName());
      UserResponseDTO.setGender(user.getGender());
      UserResponseDTO.setLastName(user.getLastName());
      UserResponseDTO.setFirstName(user.getFirstName());
      UserResponseDTO.setEmail(user.getEmail());
      UserResponseDTO.setEmail(user.getEmail());
      UserResponseDTO.setDob(user.getDob());
      UserResponseDTO.setHobbyResponseDTOS(hobbyResponseDTOS);

      return UserResponseDTO;
    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }

  @Override
  public void updateUser(Long id, UserRequestDTO userRequestDTO) {
//    checking user exists
    try {
      var optionalUser = this.userRepository.findByIdAndIsActive(id);
      if (optionalUser.isEmpty()) {
        log.info("Exception Catch::");
        throw new CustomException("User Not Found: " + id, HttpStatus.NOT_FOUND);
      }
//      save new user information
      UserEntity user = optionalUser.get();
      user.setUserName(userRequestDTO.getUserName());
      user.setFirstName(userRequestDTO.getFirstName());
      user.setLastName(userRequestDTO.getLastName());
      user.setAddress(userRequestDTO.getAddress());
      user.setDob(userRequestDTO.getDob());
      user.setEmail(userRequestDTO.getEmail());
      user.setGender(userRequestDTO.getGender());

      UserEntity savedUser = this.userRepository.save(user);

      // Fetch existing hobby mappings for the user
      List<UserHobbyMappingEntity> existingMappings = userHobbyMappingRepository.findByUserId(user);

      // Create a set of new hobby IDs
      Set<Long> newHobbyIds = userRequestDTO.getHobbyIdList().stream().map(Long::valueOf).collect(Collectors.toSet());

      System.out.println("newHobbyIds ========== " + newHobbyIds);

      // Delete old hobby mappings not present in the new hobby IDs
      for (UserHobbyMappingEntity mapping : existingMappings) {
        Long hobbyId = mapping.getHobbyId().getId();
        if (newHobbyIds.contains(hobbyId) == false) {
          userHobbyMappingRepository.delete(mapping);
        }
      }

      // Add new hobby mappings for new hobby IDs
      for (Long hobbyId : newHobbyIds) {
        if (existingMappings.stream().noneMatch(mapping -> mapping.getHobbyId().getId().equals(hobbyId))) {
          HobbyEntity hobbyEntity = hobbyRepository.findById(hobbyId).orElseThrow(() -> new CustomException("Hobby Not Found: " + hobbyId, HttpStatus.NOT_FOUND));
          UserHobbyMappingEntity newMapping = new UserHobbyMappingEntity();
          newMapping.setUserId(savedUser);
          newMapping.setHobbyId(hobbyEntity);
          userHobbyMappingRepository.save(newMapping);
        }
      }
    } catch (CustomException e) {
      log.info("Exception Catch:::::::::::::::::");
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }

  @Override
  @Transactional
  public void deleteUser(Long id) {
    try {
      Optional<UserEntity> optionalUser = userRepository.findById(id);
      if (optionalUser.isEmpty()) {
        throw new CustomException("User Not Found By ID: " + id, HttpStatus.NOT_FOUND);
      }
      UserEntity user = optionalUser.get();
      user.setActive(false);

    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void changeStatus(Long id, Boolean status) {
    try {
      Optional<UserEntity> optionalUser = this.userRepository.findById(id);
      if (optionalUser.isEmpty()) {
        throw new CustomException("User Not Found: " + id, HttpStatus.NOT_FOUND);
      }
      UserEntity user = optionalUser.get();
      if (status == true) {
        user.setActive(false);
        System.out.println("status true ================== " + status);
        userRepository.save(user);
      }
      if (status == false) {
        user.setActive(true);
        System.out.println("status true ================== " + status);
        userRepository.save(user);
      }


    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }
}
