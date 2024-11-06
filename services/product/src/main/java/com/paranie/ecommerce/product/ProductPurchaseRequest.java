package com.paranie.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product ID is required")
        Integer productId,
        @NotNull(message = "Quantity is required")
        double quantity
) {
}
