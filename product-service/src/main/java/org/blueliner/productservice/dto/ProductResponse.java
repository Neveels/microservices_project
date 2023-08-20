package org.blueliner.productservice.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class ProductResponse {
    private String name;
    private Integer price;
    private Integer amount;

}
