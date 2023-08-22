package org.blueliner.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.blueliner.userservice.model.Payment;
import org.blueliner.userservice.service.PaymentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;


}
