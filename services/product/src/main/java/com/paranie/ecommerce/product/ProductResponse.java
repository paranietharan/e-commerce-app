package com.paranie.ecommerce.product;

public record ProductResponse(
        long id,
        String name,
        String description,
        double price,
        int quantity,
        long categoryId
) {
}
