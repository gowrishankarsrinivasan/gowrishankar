package com.britishenglishcertificate.gowrishankar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.britishenglishcertificate.gowrishankar.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Bean
    // This bean provides the necessary details about users,
    // such as their username and password, to Spring Security.

    // it's implemented as a lambda function that takes a username and
    // queries the UserRepository to find a user by their email.
    // else it returns usernotfoundException
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    // This bean defines how authentication should be performed in your application.
    // It's configured to use the UserDetailsService bean to retrieve user
    // details and the PasswordEncoder bean to encode and verify passwords.

    // DaoAuthenticationProvider, a commonly used implementation provided by Spring
    // Security.
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    // This bean is responsible for encrypting and verifying passwords.
    // It's configured here to use the BCrypt hashing algorithm

    // The bcrypt algorithm securely hashes passwords using a cryptographic one-way
    // function combined with a salt to protect against rainbow table attacks.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    // This bean is responsible for authenticating users.
    // It's obtained from the AuthenticationConfiguration, which is part of Spring
    // Security.
    // The authenticationManager bean is typically used in controllers or services
    // to authenticate users.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
