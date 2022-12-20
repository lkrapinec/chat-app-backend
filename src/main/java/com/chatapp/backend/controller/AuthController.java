package com.chatapp.backend.controller;

import com.chatapp.backend.model.dto.LoginRequest;
import com.chatapp.backend.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {
  private final TokenService tokenService;

  private final AuthenticationManager authenticationManager;

  @Autowired
  public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
    this.tokenService = tokenService;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/token")
  public String token(@RequestBody LoginRequest userLogin) {
    Authentication authentication =
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
    return tokenService.generateToken(authentication);
  }

}
