package com.yeti.hospital.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

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
}
