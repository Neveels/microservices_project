package org.blueliner.productservice.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private Integer id;
    private Integer itemId;
    private Integer quantity;
    private Integer amount;
    private String paymentMode;
    private String address;

}
