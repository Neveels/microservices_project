package org.blueliner.userservice.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class OrderEvent {
    private String type;

    private OrderDto order;
}
