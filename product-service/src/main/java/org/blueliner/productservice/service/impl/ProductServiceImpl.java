package org.blueliner.productservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blueliner.productservice.dto.ProductRequest;
import org.blueliner.productservice.dto.ProductResponse;
import org.blueliner.productservice.model.Product;
import org.blueliner.productservice.repository.ProductRepository;
import org.blueliner.productservice.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Override
    public void addNewProduct(ProductRequest productRequest) {
        productRepository.save(modelMapper.map(productRequest, Product.class));
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .toList();
    }

}
