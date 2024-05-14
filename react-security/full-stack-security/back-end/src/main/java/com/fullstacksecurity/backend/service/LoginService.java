package com.fullstacksecurity.backend.service;

import com.fullstacksecurity.backend.request.dto.LoginRequestDTO;
import com.fullstacksecurity.backend.response.dto.ResponseTokenDTO;

public interface LoginService {
  ResponseTokenDTO  getToken(LoginRequestDTO loginRequestDTO);
}
