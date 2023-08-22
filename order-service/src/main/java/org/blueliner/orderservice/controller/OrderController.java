package org.blueliner.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.blueliner.orderservice.dto.OrderDto;
import org.blueliner.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderService.createOrder(orderDto);

    }

}
