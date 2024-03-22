package com.britishenglishcertificate.gowrishankar.service.impl;

import static com.britishenglishcertificate.gowrishankar.enumerated.TokenType.BEARER;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.britishenglishcertificate.gowrishankar.dto.request.LoginRequest;
import com.britishenglishcertificate.gowrishankar.dto.request.RegisterRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.AllUserDataResponse;
import com.britishenglishcertificate.gowrishankar.dto.response.LoginResponse;
import com.britishenglishcertificate.gowrishankar.dto.response.RegisterResponse;
import com.britishenglishcertificate.gowrishankar.enumerated.Role;
import com.britishenglishcertificate.gowrishankar.model.Token;
import com.britishenglishcertificate.gowrishankar.model.User;
import com.britishenglishcertificate.gowrishankar.repository.TokenRepository;
import com.britishenglishcertificate.gowrishankar.repository.UserRepository;
import com.britishenglishcertificate.gowrishankar.service.AuthenticationService;
import com.britishenglishcertificate.gowrishankar.utils.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// @SuppressWarnings("null")
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .mobile(request.getMobile())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .build();
        userRepository.save(user);
        return RegisterResponse.builder()
                .message("User registered successfully")
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                    () -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", user.getRole().toString());
            var accessToken = jwtUtil.generateToken(claims, user);
            revokeAllUserTokens(user);
            saveUserToken(user, accessToken);
            return LoginResponse.builder()
                    .message("Logged in successfully.")
                    .accessToken(accessToken)
                    .build();
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException("User not found: " + e.getMessage(), e);
        }
    }

    private void saveUserToken(User user, String accessToken) {
        var token = Token.builder()
                .user(user)
                .token(accessToken)
                .tokenType(BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtUtil.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail).orElseThrow();
            if (jwtUtil.isTokenValid(refreshToken, user)) {
                var accessToken = jwtUtil.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = LoginResponse.builder()
                        .message("New access token generated successfully.")
                        .accessToken(accessToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    @Override
    public AllUserDataResponse getAllUserData() {
        List<RegisterRequest> users = userRepository.findAll().stream()
                .map(this::mapToRegisterRequest)
                .collect(Collectors.toList());
        return AllUserDataResponse.builder()
                .users(users)
                .build();
    }

    private RegisterRequest mapToRegisterRequest(User user) {
        return RegisterRequest.builder()
                .name(user.getName())
                .mobile(user.getMobile())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().toString())
                .build();
    }

    @Override
    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public void updateUserByEmail(String email, RegisterRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        user.setName(request.getName());
        user.setMobile(request.getMobile());
        user.setName(request.getName());
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        // set other fields
        userRepository.save(user);
    }

}
