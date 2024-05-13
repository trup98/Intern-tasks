package com.fullstacksecurity.backend.service;

import com.fullstacksecurity.backend.request.dto.RoleRequestDTO;
import com.fullstacksecurity.backend.response.dto.RoleResponseDTO;

import java.util.List;

public interface RoleService {
  void addRole(RoleRequestDTO roleRequestDTO);

  List<RoleResponseDTO> getAllRole();

  RoleResponseDTO getById(Long id);

  void updateRole(Long id, RoleRequestDTO roleRequestDTO);

  void deleteRole(Long id);

  void changeStatus(Long id, Boolean status);
}
