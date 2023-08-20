package org.blueliner.productservice.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private Integer price;
    private Integer amount;

}
