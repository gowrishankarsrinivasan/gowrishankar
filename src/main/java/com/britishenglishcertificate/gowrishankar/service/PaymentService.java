package com.britishenglishcertificate.gowrishankar.service;

import java.util.List;

import com.britishenglishcertificate.gowrishankar.model.Payment;

public interface PaymentService {
    Payment savePayment(Payment payment);

    List<Payment> getAllPayments();
}
