package com.paranie.ecommerce.khafka;

import com.paranie.ecommerce.customer.CustomerResponse;
import com.paranie.ecommerce.order.PaymentMethod;
import com.paranie.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
