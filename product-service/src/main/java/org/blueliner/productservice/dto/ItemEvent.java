package org.blueliner.productservice.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class ItemEvent {
    private String type;
    private OrderDto order;
}
