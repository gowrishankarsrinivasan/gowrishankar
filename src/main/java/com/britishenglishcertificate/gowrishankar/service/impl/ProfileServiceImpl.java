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
    public ProfileResponseDto updateProfile(String id, ProfileRequestDTO request) {
        Optional<Profile> optionalProfile = repo.findById(id);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            if (request.getFirstName() != null) {
                profile.setFirst_name(request.getFirstName());
            }
            if (request.getLastName() != null) {
                profile.setLast_name(request.getLastName());
            }
            if (request.getEmail() != null) {
                profile.setEmail(request.getEmail());
            }
            if (request.getMobile() != null) {
                profile.setMobile(request.getMobile());
            }
            if (request.getAddress() != null) {
                profile.setAddress(request.getAddress());
            }
            if (request.getState() != null) {
                profile.setState(request.getState());
            }
            if (request.getCity() != null) {
                profile.setCity(request.getCity());
            }
            if (request.getPostalCode() != null) {
                profile.setPoastal_code(request.getPostalCode());
            }
            if (request.getAboutMe() != null) {
                profile.setAbout_me(request.getAboutMe());
            }
            repo.save(profile);
            return new ProfileResponseDto("Updated profile");
        } else {
            return new ProfileResponseDto("Profile not found");
        }
    }

    public Optional<Profile> getByEmail(String email) {
        return repo.findByEmail(email);
    }

}
