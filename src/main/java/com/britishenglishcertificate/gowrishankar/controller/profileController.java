package com.britishenglishcertificate.gowrishankar.controller;

import static com.britishenglishcertificate.gowrishankar.utils.MyConstant.AUTH;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.britishenglishcertificate.gowrishankar.dto.request.ProfileRequestDTO;
import com.britishenglishcertificate.gowrishankar.dto.response.ProfileResponseDto;
import com.britishenglishcertificate.gowrishankar.service.ProfileService;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProfile(@PathVariable String id, @RequestBody ProfileRequestDTO request) {
        String response = service.updateProfile(id, request);
        if ("Updated profile".equals(response)) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
