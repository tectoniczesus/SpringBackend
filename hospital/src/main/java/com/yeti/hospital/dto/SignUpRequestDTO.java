package com.yeti.hospital.dto;

import com.yeti.hospital.entity.types.RoleType;

import java.util.HashSet;
import java.util.Set;

public class SignUpRequestDTO {
    private String username;
    private String password;
    private String name;
    private Set<RoleType> roles = new HashSet<>();
}
