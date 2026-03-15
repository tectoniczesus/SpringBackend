package com.yeti.hospital.security;

import com.yeti.hospital.dto.LoginResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {
    private final AuthServices authServices;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println(authentication.getClass());
        System.out.println(" the oauth2SuccessHandler");
        System.out.println("Auth class: " + authentication.getClass());

        //log.info(" the oauth2SuccessHandler\");
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("User attributes: " + oAuth2User.getAttributes());
        System.out.println("Registration ID: " + token.getAuthorizedClientRegistrationId());

        String registrationId = token.getAuthorizedClientRegistrationId();


         ResponseEntity<LoginResponseDTO> loginResponse   = authServices.handleOAuth2LoginRequest(oAuth2User,registrationId);
        /**
         * ! the token is not showing in thread & variable
         *
         */

    }
}
