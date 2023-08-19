package org.blueliner.shopservice.controller;

import lombok.RequiredArgsConstructor;
import org.blueliner.shopservice.payload.Item;
import org.blueliner.shopservice.payload.Shop;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShopController {

    private final RestTemplate restTemplate;

    @GetMapping("/test")
    private String testMethod() {
        return "test method";
    }

    @GetMapping()
    public ResponseEntity<Shop> getShopItems() {
        Shop shop = new Shop();
        List<Object> itemList = restTemplate.getForObject("http://item-service/item", List.class);
        shop.setId(1);
        shop.setItemList(itemList);
        System.out.println(shop);
        return ResponseEntity.ok(shop);
    }
}
