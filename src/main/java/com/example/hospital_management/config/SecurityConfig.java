package com.example.hospital_management.config;

import com.example.hospital_management.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/users/register",
                                "/users/login"
                        ).permitAll()

                        // Patient APIs
                        .requestMatchers(HttpMethod.POST, "/patients")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/patients")
                        .hasAnyRole("ADMIN", "DOCTOR")

                        .requestMatchers(HttpMethod.GET, "/patients/*")
                        .hasAnyRole("ADMIN", "DOCTOR", "PATIENT")

                        .requestMatchers(HttpMethod.PUT, "/patients/*")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/patients/*")
                        .hasRole("ADMIN")


                        // Doctor APIs
                        .requestMatchers(HttpMethod.GET, "/doctors")
                        .hasAnyRole("ADMIN", "DOCTOR", "PATIENT")

                        .requestMatchers(HttpMethod.GET, "/doctors/*")
                        .hasAnyRole("ADMIN", "DOCTOR", "PATIENT")

                        .requestMatchers(HttpMethod.POST, "/doctors")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/doctors/*")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/doctors/*")
                        .hasRole("ADMIN")


                        // Appointment APIs
                        .requestMatchers(HttpMethod.GET, "/appointments")
                        .hasAnyRole("ADMIN", "DOCTOR")

                        .requestMatchers(HttpMethod.GET, "/appointments/patient/*")
                        .hasAnyRole("ADMIN", "DOCTOR", "PATIENT")

                        .requestMatchers(HttpMethod.GET, "/appointments/*")
                        .hasAnyRole("ADMIN", "DOCTOR", "PATIENT")

                        .requestMatchers(HttpMethod.POST, "/appointments")
                        .hasRole("PATIENT")

                        .requestMatchers(HttpMethod.PUT, "/appointments/*/cancel")
                        .hasRole("PATIENT")

                        .requestMatchers(HttpMethod.PUT, "/appointments/*/reschedule")
                        .hasRole("PATIENT")

                        .requestMatchers(HttpMethod.PUT, "/appointments/*/status")
                        .hasRole("DOCTOR")


                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}