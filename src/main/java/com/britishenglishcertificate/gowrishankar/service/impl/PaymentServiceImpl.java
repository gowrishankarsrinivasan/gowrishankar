package com.britishenglishcertificate.gowrishankar.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.britishenglishcertificate.gowrishankar.model.Payment;
import com.britishenglishcertificate.gowrishankar.repository.PaymentRepository;
import com.britishenglishcertificate.gowrishankar.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}