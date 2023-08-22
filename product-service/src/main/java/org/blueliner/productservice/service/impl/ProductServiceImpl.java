package org.blueliner.productservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blueliner.productservice.dto.*;
import org.blueliner.productservice.model.Item;
import org.blueliner.productservice.repository.ItemRepository;
import org.blueliner.productservice.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;
    private final KafkaTemplate<String, ItemEvent> kafkaTemplate;

    private final KafkaTemplate<String, PaymentEvent> kafkaPaymentTemplate;

    @Override
    public void addNewProduct(ProductRequest productRequest) {
        itemRepository.save(modelMapper.map(productRequest, Item.class));
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return itemRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .toList();
    }

    @KafkaListener(topics = "new-payments", groupId = "payments-group")
    @Override
    public void updateInventory(String paymentEvent) throws JsonProcessingException {

        PaymentEvent value = new ObjectMapper().readValue(paymentEvent, PaymentEvent.class);
        OrderDto order = value.getOrderDto();


//            Iterable<Item> inventories = itemRepository.findById(order.getItem());
        itemRepository.findById(order.getItemId()).ifPresentOrElse(item -> {
            item.setQuantity(item.getQuantity() - order.getQuantity());
            itemRepository.save(item);
        }, () -> {
            PaymentEvent pe = new PaymentEvent();
            pe.setOrderDto(order);
            pe.setType("PAYMENT_REVERSED");
            kafkaPaymentTemplate.send("reversed-payments", pe);
            throw new RuntimeException("Stock not available");
        });


    }

    @Override
    @KafkaListener(topics = "reversed-inventory", groupId = "inventory-group")
    public void reverseInventory(String event) {

        try {
            ItemEvent inventoryEvent = new ObjectMapper().readValue(event, ItemEvent.class);

            itemRepository.findById(inventoryEvent.getOrder().getItemId()).ifPresentOrElse(item -> {
                item.setQuantity(item.getQuantity() + inventoryEvent.getOrder().getQuantity());
                itemRepository.save(item);
            }, () -> {
                throw new RuntimeException("Stock not available");
            });

            PaymentEvent paymentEvent = new PaymentEvent();
            paymentEvent.setOrderDto(inventoryEvent.getOrder());
            paymentEvent.setType("PAYMENT_REVERSED");
            kafkaPaymentTemplate.send("reversed-payments", paymentEvent);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

}
