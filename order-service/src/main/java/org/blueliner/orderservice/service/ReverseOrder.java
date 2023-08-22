package org.blueliner.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.blueliner.orderservice.model.Order;
import org.blueliner.orderservice.repository.OrderRepository;
import org.blueliner.orderservice.utils.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReverseOrder {

    private final OrderRepository repository;

    @KafkaListener(topics = "reversed-orders", groupId = "orders-group")
    public void reverseOrder(String event) {

        try {
            OrderEvent orderEvent = new ObjectMapper().readValue(event, OrderEvent.class);
            Optional<Order> order = repository.findById(orderEvent.getOrder().getId());
            order.ifPresent(o -> {
                o.setStatus(Order.OrderStatus.FAILED);
                repository.save(o);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}