package com.yeti.hospital.entity;

import com.yeti.hospital.entity.types.AuthProviderType;
import com.yeti.hospital.entity.types.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@RequiredArgsConstructor

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "app_user",indexes = {
        @Index(name = "idx_provider_id_provider_type",columnList = "providerId, authProviderType")
})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(unique = true)
    private String username;

    private String password;

    private String providerId;
   @Enumerated(EnumType.STRING)
    private AuthProviderType authProviderType;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return username;
    }
    @ElementCollection(fetch= FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    Set<RoleType> roleType = new HashSet<>();
}
