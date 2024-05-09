package com.fullstacksecurity.backend.service;

import com.fullstacksecurity.backend.request.dto.RegisterUserDTO;
import com.fullstacksecurity.backend.response.dto.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    void registerUser(RegisterUserDTO registerUserDTO);

    Page<UserResponseDTO> getAll(Pageable pageable, String searchKey);
}
