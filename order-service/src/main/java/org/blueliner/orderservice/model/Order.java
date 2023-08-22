package org.blueliner.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_table")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer itemId;
    private Integer quantity;
    private Integer amount;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public enum OrderStatus {
        FAILED, CREATED
    }

}
