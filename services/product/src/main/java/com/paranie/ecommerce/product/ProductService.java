package com.paranie.ecommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    public Integer createProduct(@Valid ProductRequest productRequest) {
        return null;
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productPurchaseRequests) {
        return null;
    }

    public ProductResponse findById(Integer productId) {
        return null;
    }

    public List<ProductResponse> findAll() {
        return null;
    }
}
