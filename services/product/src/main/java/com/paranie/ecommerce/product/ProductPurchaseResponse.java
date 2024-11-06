package com.paranie.ecommerce.product;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Integer productId,
        String name,
        String description,
        Double quantity,
        BigDecimal price
) {
}
