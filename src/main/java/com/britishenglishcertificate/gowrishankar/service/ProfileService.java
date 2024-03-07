package com.britishenglishcertificate.gowrishankar.service;

import com.britishenglishcertificate.gowrishankar.dto.request.ProfileRequestDTO;
import com.britishenglishcertificate.gowrishankar.dto.response.ProfileResponseDto;

public interface ProfileService {
    ProfileResponseDto createProfile(ProfileRequestDTO request);

    String updateProfile(String id, ProfileRequestDTO request);

    ProfileResponseDto deleteProfile(String id);
}