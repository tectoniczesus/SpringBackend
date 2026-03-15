package com.yeti.hospital.security;

import com.yeti.hospital.dto.LoginRequestDTO;
import com.yeti.hospital.dto.LoginResponseDTO;
import com.yeti.hospital.dto.SignUpResponseDTO;
import com.yeti.hospital.entity.User;
import com.yeti.hospital.entity.types.AuthProviderType;
import com.yeti.hospital.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

  public User signUpInternal(LoginRequestDTO signUpRequestDTO , AuthProviderType authProviderType, String providerId) {
    User user = userRepository.findByUsername(signUpRequestDTO.getUsername()).orElse(null);

    if (user != null)
      throw new IllegalArgumentException("User already exists");

    user = User.builder()
            .username(signUpRequestDTO.getUsername())
            .providerId(providerId)
            .authProviderType(authProviderType)
            .build();

       if(authProviderType == AuthProviderType.EMAIL){
           user.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
       }
       return userRepository.save(user);
    //return new SignUpResponseDTO(user.getId(), user.getUsername());

  }
    public SignUpResponseDTO signup(LoginRequestDTO signupRequestDTO){
        User user = signUpInternal(signupRequestDTO,AuthProviderType.EMAIL,null);
        return new SignUpResponseDTO(user.getId(),user.getUsername());

    }
    @Transactional
    public ResponseEntity<LoginResponseDTO> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {
    //provider type
        //provider id
        //save the providerType and id
        //if already has account then login else signup and login
        AuthProviderType providerType = authUtil.getProviderTypeFromRegistration(registrationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User,registrationId);
      User user = userRepository.findByProviderIdAndAuthProviderType(providerId,providerType).orElse(null);
      String email = oAuth2User.getAttribute("email");
      //String name = oAuth2User.getAttribute("name");
        User userEmail = userRepository.findByUsername(email).orElse(null);
        if(user==null && userEmail==null){
            String userName = authUtil.determineUserFromOauth2User(oAuth2User,registrationId,providerId);
            user = signUpInternal(new LoginRequestDTO(userName,null),providerType,providerId);
        }else if(user!=null){
            if (email!=null && !email.isBlank() && !email.equals(user.getUsername())){
             user.setUsername(email);
             userRepository.save(user);
            }
        }else{
            throw  new BadCredentialsException("this email is already registered with provider " + userEmail.getAuthProviderType());
        }

       LoginResponseDTO loginResponseDTO = new LoginResponseDTO(authUtil.generateAccessToken(user),user.getId());

       return ResponseEntity.ok(loginResponseDTO);
        //return null;

    }
}
