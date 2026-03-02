package com.yeti.hospital.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class webSecurityConfig {
        private final PasswordEncoder passwordEncoder;

        @Bean
        // * the route localhost:8080/patient/allPatient is working fine as public
        // ? but all me the other routes are giving error of 403forbidden even after
        // ?adding perfect username and pass

        /*
         * * all the other routes are working fine only error was that
         * the route starting from requestMatchers was not created at first
         * example localhost:8080/patient/allPatient will work because patient route
         * exits
         * example2 localhost:8080/admin/allPatient will throw 403 error even if user
         * and password is correct
         * example 3 localhost:8080/admin/patient/allPatient will throw 403 error too
         */

        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity
                                .cors(Customizer.withDefaults())
                                .csrf(csrfConfig -> csrfConfig.disable())
                                .sessionManagement(sessionConfig -> sessionConfig
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                                .requestMatchers("/public/**", "/api/v1/auth/**", "/error").permitAll()
                                                .requestMatchers("/patient/**").hasRole("ADMIN")
                                                .requestMatchers("/doctor/**").hasAnyRole("DOCTOR", "ADMIN")
                                                .anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults());;
                // .formLogin(Customizer.withDefaults());
                /**
                 * ! giving 403 error
                 * * resolved
                 */
                return httpSecurity.build();
        }
//
//        @Bean
//        UserDetailsService userDetailsService() {
//                UserDetails user1 = User.withUsername("admin")
//                                .password(passwordEncoder.encode("pass"))
//                                .roles("ADMIN")
//                                .build();
//                UserDetails user2 = User.withUsername("doctor")
//                                .password(passwordEncoder.encode("pass"))
//                                .roles("DOCTOR")
//                                .build();
//
//                return new InMemoryUserDetailsManager(user1, user2);
//        }

//        @Bean
//        CorsConfigurationSource corsConfigurationSource() {
//                CorsConfiguration configuration = new CorsConfiguration();
//                configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://127.0.0.1:3000"));
//                configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//                configuration.setAllowedHeaders(List.of("*"));
//                configuration.setAllowCredentials(true);
//                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//                source.registerCorsConfiguration("/**", configuration);
//                return source;
//        }

}
