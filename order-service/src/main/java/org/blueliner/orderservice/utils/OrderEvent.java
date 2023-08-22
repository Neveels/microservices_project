package org.blueliner.orderservice.utils;

import lombok.*;
import org.blueliner.orderservice.dto.OrderDto;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private String type;
    private OrderDto order;

}
