package com.britishenglishcertificate.gowrishankar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.britishenglishcertificate.gowrishankar.model.PaymentModel;

public interface PaymentRepo extends JpaRepository<PaymentModel, String> {

}