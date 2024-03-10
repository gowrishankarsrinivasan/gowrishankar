package com.britishenglishcertificate.gowrishankar.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.britishenglishcertificate.gowrishankar.model.Payment;
import com.britishenglishcertificate.gowrishankar.service.PaymentService;
import static com.britishenglishcertificate.gowrishankar.utils.MyConstant.AUTH;

@RestController
@RequestMapping(AUTH)
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/save")
    public Payment savePayment(@RequestBody Payment payment) {
        return paymentService.savePayment(payment);

    }

    @GetMapping("/get")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();

    }
}