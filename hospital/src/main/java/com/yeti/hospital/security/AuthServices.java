package com.yeti.hospital.security;

import com.yeti.hospital.dto.LoginRequestDTO;
import com.yeti.hospital.dto.LoginResponseDTO;
import com.yeti.hospital.dto.SignUpResponseDTO;
import com.yeti.hospital.entity.User;
import com.yeti.hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServices {
  private final AuthenticationManager authenticationManager;
  private final AuthUtil authUtil;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDTO(token, user.getId());
    }

  public SignUpResponseDTO signup(LoginRequestDTO signUpRequestDTO) {
    User user = userRepository.findByUsername(signUpRequestDTO.getUsername()).orElse(null);

    if (user != null)
      throw new IllegalArgumentException("User already exists");

    user = userRepository.save(User.builder()
        .username(signUpRequestDTO.getUsername())
        .password(passwordEncoder.encode(signUpRequestDTO.getPassword()))
        .build());
    return new SignUpResponseDTO(user.getId(), user.getUsername());

  }

    public ResponseEntity<LoginResponseDTO> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {
    //provider type
        //provider id
        //save the providerType and id
        //if already has account then login else signup and login

        return null;

    }
}
