package com.yeti.hospital.security;

import com.yeti.hospital.entity.User;
import com.yeti.hospital.entity.types.AuthProviderType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Component
@Slf4j
public class AuthUtil {
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    /*
    ?create a method to convert key to hmacShakeyFor jwt
     * DONE
     **/


    private SecretKey secretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user){
         return Jwts.builder()
                 .subject(user.getUsername())
                 .claim("userId",user.getId().toString())
                 .issuedAt(new Date())
                 .expiration(new Date(System.currentTimeMillis()+ 1000*60*10))
                 .signWith(secretKey())
                 .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return  claims.getSubject();
    }

    public AuthProviderType getProviderTypeFromRegistration(String registrationId){
        return switch (registrationId.toLowerCase()){
            case "google" -> AuthProviderType.GOOGLE;
            case "github" -> AuthProviderType.GITHUB;
            case "facebook" -> AuthProviderType.FACEBOOK;
            default -> throw new IllegalArgumentException("Unsupported argument type " + registrationId);
        };
    }

    public String determineProviderIdFromOAuth2User(OAuth2User oAuth2User,String registrationId){
        String providerId = switch (registrationId.toLowerCase()){
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("id").toString();
            default -> {
                //log.error("unsupported OAuth2 provider {}" + registrationId);
                throw new IllegalArgumentException("Unsupport OAuth2 provider" + registrationId);
            }
        };
        if(providerId==null || providerId.isBlank()){
            throw new IllegalArgumentException("unable to determine providerId for OAuth2 login");
        }

        return providerId;
    }

    public String determineUserFromOauth2User(OAuth2User oauth2User,String registrationId,String providerId ){
        String email  = oauth2User.getAttribute("email");
        if(email==null && !email.isBlank()){
            return email;
        }
        return switch (registrationId.toLowerCase()){
            case "google" -> oauth2User.getAttribute("sub");
            case "github" -> oauth2User.getAttribute("login");

            default -> providerId;
        };
    }

}
