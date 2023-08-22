package org.blueliner.userservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer id;
    private Integer itemId;
    private Integer quantity;
    private Integer amount;
    private String paymentMode;
    private String address;

}
