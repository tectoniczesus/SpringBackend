package com.yeti.hospital.controller;

import com.yeti.hospital.dto.LoginRequestDTO;
import com.yeti.hospital.dto.LoginResponseDTO;
import com.yeti.hospital.dto.SignUpResponseDTO;
import com.yeti.hospital.security.AuthServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthServices authServices;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
    return ResponseEntity.ok(authServices.login(loginRequestDTO));
  }

  @PostMapping("/signup")
  public ResponseEntity<SignUpResponseDTO> signup(@RequestBody LoginRequestDTO signUpRequestDTO) {
    return ResponseEntity.ok(authServices.signup(signUpRequestDTO));
  }
}
