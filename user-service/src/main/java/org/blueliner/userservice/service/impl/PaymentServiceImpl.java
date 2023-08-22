package org.blueliner.userservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.blueliner.userservice.dto.OrderDto;
import org.blueliner.userservice.dto.OrderEvent;
import org.blueliner.userservice.dto.PaymentEvent;
import org.blueliner.userservice.model.Payment;
import org.blueliner.userservice.repository.PaymentRepository;
import org.blueliner.userservice.service.PaymentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    private final KafkaTemplate<String, OrderEvent> kafkaOrderTemplate;

    @KafkaListener(topics = "new-orders", groupId = "orders-group")
    @Override
    public void processPayment(String event) throws JsonProcessingException {

        OrderEvent orderEvent = new ObjectMapper().readValue(event, OrderEvent.class);

        OrderDto order = orderEvent.getOrder();
        Payment payment = new Payment();
        try {

            payment.setAmount(order.getAmount());
            payment.setMode(order.getPaymentMode());
            payment.setOrderId(order.getId());
            payment.setStatus("SUCCESS");
            paymentRepository.save(payment);

            PaymentEvent paymentEvent = new PaymentEvent();
            paymentEvent.setOrderDto(orderEvent.getOrder());
            paymentEvent.setType("PAYMENT_CREATED");
            kafkaTemplate.send("new-payments", paymentEvent);
        } catch (Exception e) {

            payment.setOrderId(order.getId());
            payment.setStatus("FAILED");
            paymentRepository.save(payment);

            OrderEvent oe = new OrderEvent();
            oe.setOrder(order);
            oe.setType("ORDER_REVERSED");
            kafkaOrderTemplate.send("reversed-orders", orderEvent);

        }

    }

    @KafkaListener(topics = "reversed-payments", groupId = "payments-group")
    @Override
    public void reversePayment(String event) {
        try {
            PaymentEvent paymentEvent = new ObjectMapper().readValue(event, PaymentEvent.class);
            OrderDto order = paymentEvent.getOrderDto();

            //Iterable<Payment> payments = paymentRepository.findByOrderId(order.getOrderId());
            List<Payment> paymentList = paymentRepository.findByOrderId(order.getId());
            paymentList.forEach(p -> {
                p.setStatus("FAILED");
                paymentRepository.save(p);
            });

            OrderEvent orderEvent = new OrderEvent();
            orderEvent.setOrder(paymentEvent.getOrderDto());
            orderEvent.setType("ORDER_REVERSED");
            kafkaOrderTemplate.send("reversed-orders", orderEvent);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
