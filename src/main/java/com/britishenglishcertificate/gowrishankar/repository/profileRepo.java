package com.britishenglishcertificate.gowrishankar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.britishenglishcertificate.gowrishankar.dto.request.ProfileRequestDTO;
import com.britishenglishcertificate.gowrishankar.dto.response.ProfileResponseDto;
import com.britishenglishcertificate.gowrishankar.model.Profile;

public interface profileRepo extends JpaRepository<Profile, String> {

    ProfileResponseDto saveAndFlush(ProfileRequestDTO request);

    Optional<Profile> findByEmail(String id);

}
