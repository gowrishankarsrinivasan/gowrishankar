package com.britishenglishcertificate.gowrishankar.controller;

import static com.britishenglishcertificate.gowrishankar.utils.MyConstant.AUTH;
import static com.britishenglishcertificate.gowrishankar.utils.MyConstant.LOGIN;
import static com.britishenglishcertificate.gowrishankar.utils.MyConstant.REFRESR_TOKEN;
import static com.britishenglishcertificate.gowrishankar.utils.MyConstant.REGISTER;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.britishenglishcertificate.gowrishankar.dto.request.LoginRequest;
import com.britishenglishcertificate.gowrishankar.dto.request.RegisterRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.LoginResponse;
import com.britishenglishcertificate.gowrishankar.dto.response.RegisterResponse;
import com.britishenglishcertificate.gowrishankar.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(AUTH)

@RequiredArgsConstructor

public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();
        try {
            response = authService.register(request);
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Registration failed due to an unexpected error.");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        LoginResponse response = new LoginResponse();
        try {
            response = authService.login(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Login failed!");
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(REFRESR_TOKEN)
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }
}
