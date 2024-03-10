package com.britishenglishcertificate.gowrishankar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.britishenglishcertificate.gowrishankar.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Add custom query methods if needed
}