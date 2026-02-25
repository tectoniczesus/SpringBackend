package com.yeti.hospital.security;

import org.springframework.beans.factory.annotation.Value;

public class AuthUtil {
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    /*
    ?create a method to convert key to hmacShakeyFor jwt
     **/
}
