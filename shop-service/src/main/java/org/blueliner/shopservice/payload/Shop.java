package org.blueliner.shopservice.payload;

import lombok.*;

import java.util.List;

@Setter
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    private int id;
    private List<Object> itemList;

}
