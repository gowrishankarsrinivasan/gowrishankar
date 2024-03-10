package com.britishenglishcertificate.gowrishankar.controller;

import static com.britishenglishcertificate.gowrishankar.utils.MyConstant.AUTH;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.britishenglishcertificate.gowrishankar.dto.request.ProfileRequestDTO;
import com.britishenglishcertificate.gowrishankar.dto.response.ProfileResponseDto;
import com.britishenglishcertificate.gowrishankar.model.Profile;
import com.britishenglishcertificate.gowrishankar.service.ProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(AUTH)
public class profileController {
    private final ProfileService service;

    public profileController(ProfileService service) {
        this.service = service;
    }

    @PostMapping("/profile")
    public ResponseEntity<ProfileResponseDto> createProfile(@RequestBody ProfileRequestDTO request) {
        ProfileResponseDto response = service.createProfile(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/profile/patch/{id}")
    public ResponseEntity<ProfileResponseDto> updateProfile(@PathVariable String id,
            @RequestBody ProfileRequestDTO request) {
        ProfileResponseDto responseDto = service.updateProfile(id, request);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/profile/{email}")
    public Optional<Profile> getProfileByEmail(@PathVariable String email) {
        return service.getByEmail(email);
    }

}
