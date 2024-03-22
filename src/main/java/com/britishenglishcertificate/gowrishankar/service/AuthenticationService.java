package com.britishenglishcertificate.gowrishankar.service;

import java.io.IOException;

import com.britishenglishcertificate.gowrishankar.dto.request.LoginRequest;
import com.britishenglishcertificate.gowrishankar.dto.request.RegisterRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.AllUserDataResponse;
import com.britishenglishcertificate.gowrishankar.dto.response.LoginResponse;
import com.britishenglishcertificate.gowrishankar.dto.response.RegisterResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    AllUserDataResponse getAllUserData();

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void deleteUserByEmail(String email);

    void updateUserByEmail(String email, RegisterRequest request);
}
