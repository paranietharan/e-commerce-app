package com.paranie.ecommerce.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public List<ProductResponse> getAllproducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::toProductResponse)
                .collect(Collectors.toList());
    }

    private ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory() != null ? product.getCategory().getId() : 0 // Handle null category
        );
    }
}
