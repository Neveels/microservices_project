package org.blueliner.itemservice.controller;

import org.blueliner.itemservice.payload.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    List<Item> itemList;

    {
        itemList = Arrays.asList(
                new Item(1, "1"),
                new Item(2, "2")
        );
    }

    @GetMapping
    public List<Item> getAll() {
        return itemList;
    }
}
