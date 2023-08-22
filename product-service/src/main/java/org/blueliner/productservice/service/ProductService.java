package org.blueliner.productservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.blueliner.productservice.dto.ProductRequest;
import org.blueliner.productservice.dto.ProductResponse;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

public interface ProductService {
    void addNewProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProduct();

//    @KafkaListener(topics = "new-payments", groupId = "payments-group")
    //TODO: check if can i use annotation in interface
    void updateInventory(String paymentEvent) throws JsonMappingException, JsonProcessingException;

    void reverseInventory(String event);
}
