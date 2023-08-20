package org.blueliner.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.blueliner.productservice.dto.ProductRequest;
import org.blueliner.productservice.dto.ProductResponse;
import org.blueliner.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public void addNewProduct(@RequestBody ProductRequest productRequest) {
        productService.addNewProduct(productRequest);
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAllProduct();
    }
}
