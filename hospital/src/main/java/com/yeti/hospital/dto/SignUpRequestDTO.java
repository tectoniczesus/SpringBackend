package com.yeti.hospital.dto;

import com.yeti.hospital.entity.types.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {
    private String username;
    private String password;
    private String name;
    private Set<RoleType> roles = new HashSet<>();
}
