package com.fullstacksecurity.backend.service.impl;

import com.fullstacksecurity.backend.config.modelMapper.ModalMapperConfig;
import com.fullstacksecurity.backend.entity.RoleEntity;
import com.fullstacksecurity.backend.entity.UserEntity;
import com.fullstacksecurity.backend.enums.ExceptionEnum;
import com.fullstacksecurity.backend.exception.CustomException;
import com.fullstacksecurity.backend.repository.RoleRepository;
import com.fullstacksecurity.backend.request.dto.RoleRequestDTO;
import com.fullstacksecurity.backend.response.dto.RoleResponseDTO;
import com.fullstacksecurity.backend.service.RoleService;
import com.fullstacksecurity.backend.utill.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;
  private final Utilities utilities;
  private final ModalMapperConfig modalMapperConfig;

  private RoleResponseDTO mapEntityToDto(RoleEntity roleEntity) {
    return modalMapperConfig.modelMapper().map(roleEntity, RoleResponseDTO.class);
  }

  @Override
  public void addRole(RoleRequestDTO roleRequestDTO) {
    try {
      var user = this.roleRepository.findByRole(roleRequestDTO.getRoleName());

      if (user.isPresent()) {
        throw new CustomException(ExceptionEnum.ROLE_ALREADY_EXIST.getMessage(), HttpStatus.BAD_REQUEST);
      }

      UserEntity currentUser = utilities.currentUser();

      System.out.println("currentUser ================ " + currentUser);
      RoleEntity roleEntity = new RoleEntity();
      roleEntity.setCreatedBy(currentUser);
      roleEntity.setUpdatedBy(currentUser);
      roleEntity.setRole(roleRequestDTO.getRoleName());

      log.info("Role Saved::::{}", roleEntity);

      this.roleRepository.save(roleEntity);
    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }

  @Override
  public List<RoleResponseDTO> getAllRole() {
    try {
      return this.roleRepository.findAll().stream().map(this::mapEntityToDto).collect(Collectors.toList());
    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }

  }

  @Override
  public RoleResponseDTO getById(Long id) {
    try {
      var role = this.roleRepository.findById(id);
      if (role.isPresent()) {
        return this.modalMapperConfig.modelMapper().map(role, RoleResponseDTO.class);
      } else {
        throw new CustomException(ExceptionEnum.ROLE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
      }
    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }

  @Override
  public void updateRole(Long id, RoleRequestDTO roleRequestDTO) {
    try {
      var role = this.roleRepository.findById(id);
      UserEntity currentUser = utilities.currentUser();
      if (role.isPresent()) {
        RoleEntity currentRole = role.get();
        currentRole.setRole(roleRequestDTO.getRoleName());
        currentRole.setCreatedBy(currentUser);
        currentRole.setUpdatedBy(currentUser);
        this.roleRepository.save(currentRole);
      } else {
        throw new CustomException(ExceptionEnum.ROLE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
      }

    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }

  @Override
  public void deleteRole(Long id) {
    try {
      var user = this.roleRepository.findById(id);
      if (user.isPresent()) {
        RoleEntity role = user.get();
        role.setActive(false);
        role.setDelete(true);
        this.roleRepository.save(role);
      } else {
        throw new CustomException(ExceptionEnum.ROLE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
      }
    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }

  @Override
  public void changeStatus(Long id, Boolean status) {
    try {
      var user = this.roleRepository.findById(id);
      if (user.isPresent()) {
        RoleEntity role = user.get();
        if (status) {
          role.setActive(false);
          log.info("Role Changed::::::::::::");
          this.roleRepository.save(role);
        } else {
          role.setActive(true);
          log.info("Role Changed::::::::::::");
          this.roleRepository.save(role);
        }
      } else {
        throw new CustomException(ExceptionEnum.ROLE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
      }
    } catch (CustomException e) {
      throw new CustomException(e.getMessage(), e.getHttpStatus());
    }
  }
}
