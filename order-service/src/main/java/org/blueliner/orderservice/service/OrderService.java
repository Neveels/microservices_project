package org.blueliner.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.blueliner.orderservice.dto.OrderDto;
import org.blueliner.orderservice.model.Order;
import org.blueliner.orderservice.repository.OrderRepository;
import org.blueliner.orderservice.utils.OrderEvent;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final ModelMapper modelMapper;

    public void createOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        try {
            order.setStatus(Order.OrderStatus.CREATED);
            order = orderRepository.save(order);
            orderDto.setId(order.getId());
            OrderEvent event = new OrderEvent();
            event.setOrder(orderDto);
            event.setType("ORDER_CREATED");
            kafkaTemplate.send("new-orders", event);
        } catch (Exception e) {
            order.setStatus(Order.OrderStatus.FAILED);
            orderRepository.save(order);
        }

    }

}
