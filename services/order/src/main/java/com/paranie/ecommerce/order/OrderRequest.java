package com.paranie.ecommerce.order;

import com.paranie.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,

        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,

        @NotNull(message = "customer should be present")
        @NotEmpty(message = "customer should be present")
        @NotBlank(message = "customer should be present")
        String customerId,

        @NotEmpty(message = "You should provide at least one product")
        List<PurchaseRequest> products
) {
}
