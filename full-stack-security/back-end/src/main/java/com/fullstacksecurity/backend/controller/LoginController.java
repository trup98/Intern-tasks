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
@RequestMapping("/api/auth")
@Slf4j
public class LoginController {

  private final LoginService loginService;

  @GetMapping("/hello")
  @PreAuthorize("hasAnyAuthority('ROLE_USER')")
  public String helloUser() {
    return "Hello User";
  }

  @GetMapping("/helloAdmin")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public String helloAdmin() {
    return "hello Admin";
  }

  @PostMapping("/getToken")
  public ResponseEntity<ApiResponse> getToken(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
    log.info("Login User Details::::::", loginRequestDTO);
    ResponseTokenDTO responseTokenDTO = this.loginService.getToken(loginRequestDTO);
    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Logged In User", responseTokenDTO), HttpStatus.OK);

  }
}
