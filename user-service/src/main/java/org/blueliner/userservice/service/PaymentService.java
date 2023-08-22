package org.blueliner.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface PaymentService {
    void processPayment(String event) throws JsonProcessingException;
    void reversePayment(String event);
}
