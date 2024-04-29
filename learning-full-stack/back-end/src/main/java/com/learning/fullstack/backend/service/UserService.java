package com.learning.fullstack.backend.service;

import com.learning.fullstack.backend.dto.UserRequestDTO;
import com.learning.fullstack.backend.dto.UserResponseDTO;
import com.learning.fullstack.backend.projection.GetAllUserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
  void saveUser(UserRequestDTO userRequestDTO);

  Page<GetAllUserProjection> findUser(Pageable pageable, String searchKey);

  UserResponseDTO findById(Long id);

  void updateUser(Long id, UserRequestDTO userRequestDTO);

  void deleteUser(Long id);

  void changeStatus(Long id, Boolean status);
}
