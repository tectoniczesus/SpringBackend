package com.yeti.hospital.security;

import com.yeti.hospital.dto.LoginRequestDTO;
import com.yeti.hospital.dto.LoginResponseDTO;
import com.yeti.hospital.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServices {
    private final AuthenticationManager authenticationManager;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername() ,loginRequestDTO.getPassword())
        );
          User user = (User) authentication.getPrincipal();
          /*
           ? add jswon web token dependency in pom.xml file then return the LoginResponseDTO
          */


          return  null;
    }
}
