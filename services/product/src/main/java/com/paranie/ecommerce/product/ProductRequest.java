package com.paranie.ecommerce.product;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @Positive(message = "Product available quantity must be positive")
        double avilableQuantity,
        @Positive(message = "Product price must be positive")
        BigDecimal price,
        @NotNull(message = "Product category id is required")
        Integer categoryId
) {
}
