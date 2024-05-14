package com.fullstacksecurity.backend.service;

import com.fullstacksecurity.backend.projection.GetAllUserDetails;
import com.fullstacksecurity.backend.request.dto.RegisterUserDTO;
import com.fullstacksecurity.backend.request.dto.UpdateUserDTO;
import com.fullstacksecurity.backend.response.dto.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
  void registerUser(RegisterUserDTO registerUserDTO);

  Page<GetAllUserDetails> getAll(Pageable pageable, String searchKey);

  UserResponseDTO getUserById(Long id);

  void changeStatus(Long id, Boolean status);

  void updateUser(Long id, UpdateUserDTO updateUserDTO);

  void deleteUser(Long id);
}
