package com.britishenglishcertificate.gowrishankar.service;

import java.util.Optional;

import com.britishenglishcertificate.gowrishankar.dto.request.ProfileRequestDTO;
import com.britishenglishcertificate.gowrishankar.dto.response.ProfileResponseDto;
import com.britishenglishcertificate.gowrishankar.model.Profile;

public interface ProfileService {
    ProfileResponseDto createProfile(ProfileRequestDTO request);

    ProfileResponseDto updateProfile(String id, ProfileRequestDTO request);

    Optional<Profile> getByEmail(String email);

    // ProfileResponseDto getProfileByEmail(String email, ProfileRequestDTO
    // request);
}