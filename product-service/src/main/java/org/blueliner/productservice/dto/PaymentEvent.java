package org.blueliner.productservice.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class PaymentEvent {
    private String type;
    private OrderDto orderDto;
}
