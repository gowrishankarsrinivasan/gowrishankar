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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.britishenglishcertificate.gowrishankar.dto.request.LoginRequest;
import com.britishenglishcertificate.gowrishankar.dto.request.RegisterRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.AllUserDataResponse;
import com.britishenglishcertificate.gowrishankar.dto.response.LoginResponse;
import com.britishenglishcertificate.gowrishankar.dto.response.RegisterResponse;
import com.britishenglishcertificate.gowrishankar.repository.UserRepository;
import com.britishenglishcertificate.gowrishankar.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(AUTH)
// @PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor

public class AuthenticationController {

    private final AuthenticationService authService;

    @SuppressWarnings("unused")
    private final UserRepository userRepository;

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

    @GetMapping("/all")
    // @PreAuthorize("hasAuthority('admin:read')")
    public AllUserDataResponse getAllUsers() {
        return authService.getAllUserData();
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUserByEmail(@RequestParam String email) {
        try {
            authService.deleteUserByEmail(email);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{email}")
    public ResponseEntity<?> updateUser(@PathVariable String email, @RequestBody RegisterRequest request) {
        try {
            authService.updateUserByEmail(email, request);
            return ResponseEntity.ok().body("User updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user.");
        }
    }

}
