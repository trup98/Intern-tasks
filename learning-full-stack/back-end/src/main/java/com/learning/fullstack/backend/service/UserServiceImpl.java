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
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserHobbyMappingRepository userHobbyMappingRepository;
  private final HobbyRepository hobbyRepository;
  private final ModelMapper modelMapper;

  public UserResponseDTO EntityToDto(UserEntity userEntity) {
    return modelMapper.map(userEntity, UserResponseDTO.class);
  }

  @Override
  public void saveUser(UserRequestDTO userRequestDTO) {

    try {
      // check user exists
      var user = userRepository.findByEmail(userRequestDTO.getEmail());
      if (user.isPresent()) {
        throw new CustomException("User Already Exist!!", HttpStatus.NOT_ACCEPTABLE);
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
        HobbyEntity hobbyEntity = this.hobbyRepository.findById(Long.valueOf(hobbyId)).orElseThrow(() -> new CustomException("Not Found: " + hobbyId, HttpStatus.NOT_FOUND));

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
      var optionalUser = this.userRepository.findById(id);
      if (optionalUser.isEmpty()) {
        log.info("Exception Catch::::");
        throw new CustomException("User Not Found: " + id, HttpStatus.NOT_FOUND);
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
      var optionalUser = this.userRepository.findByEmail(userRequestDTO.getEmail());
      if (optionalUser.isEmpty()) {
        log.info("Exception Catch::");
        throw new CustomException("User Not Found: " + id, HttpStatus.NOT_FOUND);
      }
      UserEntity user = optionalUser.get();
      user.setUserName(userRequestDTO.getUserName());
      user.setFirstName(userRequestDTO.getFirstName());
      user.setLastName(userRequestDTO.getLastName());
      user.setAddress(userRequestDTO.getAddress());
      user.setDob(userRequestDTO.getDob());
      user.setEmail(userRequestDTO.getEmail());
      user.setGender(userRequestDTO.getGender());

      UserEntity userEntity = this.userRepository.save(user);


      List<UserHobbyMappingEntity> byUserId = userHobbyMappingRepository.findByUserId(user);

      List<HobbyEntity> dbHobby = new ArrayList<>();
      for (UserHobbyMappingEntity userHobbyMappingEntity : byUserId) {
        HobbyEntity hobbyEntity = userHobbyMappingEntity.getHobbyId();
        dbHobby.add(hobbyEntity);
      }
      System.out.println("Old Hobby  ====================== " + dbHobby);

      List<HobbyEntity> newHobby = new ArrayList<>();

      userRequestDTO.getHobbyIdList().forEach(hobby -> {
        HobbyEntity hobbyEntity = hobbyRepository.findById(hobby.longValue()).orElseThrow(() -> new CustomException("Hobby Not Found::", HttpStatus.NOT_FOUND));
        newHobby.add(hobbyEntity);
      });
      System.out.println("new hobby ============== " + newHobby);

      Set<Long> existingHobbyId = dbHobby.stream().map(HobbyEntity::getId).collect(Collectors.toSet());

      List<HobbyEntity> hobbyEntitiesToAdd = new ArrayList<>();

      for (HobbyEntity hobbyEntity : newHobby) {
        if (!existingHobbyId.contains(hobbyEntity.getId())) {
          hobbyEntitiesToAdd.add(hobbyEntity);
        }
      }

      for (HobbyEntity hobbyEntity : hobbyEntitiesToAdd) {
        UserHobbyMappingEntity userHobbyMappingEntity = new UserHobbyMappingEntity();
        userHobbyMappingEntity.setUserId(user);
        userHobbyMappingEntity.setHobbyId(hobbyEntity);
        userHobbyMappingRepository.save(userHobbyMappingEntity);
      }


      if (byUserId.isEmpty()) {
        throw new CustomException("Hobby Id not Exist: " + byUserId, HttpStatus.NOT_FOUND);
      }


//      userRequestDTO.getHobbyIdList().forEach(hobbyId -> {
//          HobbyEntity hobbyEntity = this.hobbyRepository.findById(Long.valueOf(hobbyId)).orElseThrow(() -> new CustomException("Hobby Id Not Found: " + id, HttpStatus.NOT_FOUND));
//        }


    } catch (CustomException e) {
      log.info("Exception Catch:::::::::::::::::");
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }
}
