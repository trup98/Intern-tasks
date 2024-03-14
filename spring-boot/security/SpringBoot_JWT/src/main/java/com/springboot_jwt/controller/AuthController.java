package com.springboot_jwt.controller;

import com.springboot_jwt.dto.AuthRequestDto;
import com.springboot_jwt.dto.RefreshTokenRequest;
import com.springboot_jwt.dto.ResponseToken;
import com.springboot_jwt.utils.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    /*For Generating Token*/
    @PostMapping("/authenticate")
    @Operation(summary = "For Generating Token", description = "for Admin userName:trup  " +
            "  password:000000  " +
            "  for User userName:user  " +
            "  password:111111")
    public ResponseToken authenticateAndGetToken(@RequestBody AuthRequestDto authRequestDto) {
        Authentication authenticate = authenticationManager.
                authenticate(new
                        UsernamePasswordAuthenticationToken(authRequestDto.getUserName(),
                        authRequestDto.getPassword()));
        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(authRequestDto.getUserName());
        } else {
            throw new UsernameNotFoundException("Invalid User Request");
        }
    }

    @PostMapping("/refreshToken")
    @Operation(summary = "For Generating Refresh Token")
    public ResponseToken refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return jwtService.createRefreshToken(refreshTokenRequest);
    }

}
