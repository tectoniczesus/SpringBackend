package com.yeti.hospital.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class webSecurityConfig {
     private final PasswordEncoder passwordEncoder;
    @Bean
   // *  the route localhost:8080/patient/allPatient is working fine as public
    //? but all me the other routes are giving error of 403forbidden even after
    // ?adding perfect username and pass

    /* *  all the other routes are working fine only error was that
     * the route starting from requestMatchers was not created at first
    * example localhost:8080/patient/allPatient will work because patient route exits
    *  example2 localhost:8080/admin/allPatient will throw 403 error even if user and password is correct
    *  example 3 localhost:8080/admin/patient/allPatient will throw 403 error too
   */

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.
                authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/patient/**").permitAll()
                        .requestMatchers("/patient/**").hasRole("ADMIN")
                        .requestMatchers("/doctor/**").hasAnyRole("DOCTOR","ADMIN")
                )
                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    UserDetailsService userDetailsService(){
        UserDetails user1 = User.withUsername("admin")
                .password(passwordEncoder.encode("pass"))
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.withUsername("doctor")
                .password(passwordEncoder.encode("pass"))
                .roles("DOCTOR")
                .build();

        return new InMemoryUserDetailsManager(user1,user2);
    }

}
