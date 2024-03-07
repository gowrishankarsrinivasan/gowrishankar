package com.britishenglishcertificate.gowrishankar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.britishenglishcertificate.gowrishankar.model.User;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

}
