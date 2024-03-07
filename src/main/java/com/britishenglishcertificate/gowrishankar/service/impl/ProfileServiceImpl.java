package com.britishenglishcertificate.gowrishankar.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.britishenglishcertificate.gowrishankar.dto.request.ProfileRequestDTO;
import com.britishenglishcertificate.gowrishankar.dto.response.ProfileResponseDto;
import com.britishenglishcertificate.gowrishankar.model.Profile;
import com.britishenglishcertificate.gowrishankar.repository.profileRepo;
import com.britishenglishcertificate.gowrishankar.service.ProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final profileRepo repo;

    @Override
    public ProfileResponseDto createProfile(ProfileRequestDTO request) {
        var profileDate = Profile.builder()
                .first_name(request.getFirstName())
                .last_name(request.getLastName())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .address(request.getAddress())
                .state(request.getState())
                .city(request.getCity())
                .poastal_code(request.getPostalCode())
                .about_me(request.getAboutMe()).build();
        repo.save(profileDate);
        return new ProfileResponseDto("Saved profile");
    }

    @Override
    public String updateProfile(String id, ProfileRequestDTO request) {
        Optional<Profile> existingProfile = repo.findById(id);
        if (existingProfile.isPresent()) {
            Profile profileData = existingProfile.get();
            profileData.setFirst_name(request.getFirstName());
            profileData.setLast_name(request.getLastName());
            profileData.setEmail(request.getEmail());

            repo.save(profileData);
            return "Updated profile"; // Changed to "Updated profile" for consistency
        }
        return "not successful";
    }

    @Override
    public ProfileResponseDto deleteProfile(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProfile'");
    }

}
