package com.fullstacksecurity.backend.controller;

import com.fullstacksecurity.backend.request.dto.LoginRequestDTO;
import com.fullstacksecurity.backend.response.dto.ApiResponse;
import com.fullstacksecurity.backend.response.dto.ResponseTokenDTO;
import com.fullstacksecurity.backend.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Slf4j
@CrossOrigin("http://localhost:3001")
public class LoginController {

  private final LoginService loginService;

  @PostMapping("/login")
  public ResponseEntity<ApiResponse> getToken(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
    log.info("Login User Details::::::", loginRequestDTO);
    ResponseTokenDTO responseTokenDTO = this.loginService.getToken(loginRequestDTO);
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Logged In User", responseTokenDTO), HttpStatus.OK);

  }
}
