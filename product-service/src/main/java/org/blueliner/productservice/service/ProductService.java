package org.blueliner.productservice.service;

import org.blueliner.productservice.dto.ProductRequest;
import org.blueliner.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void addNewProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProduct();

}
