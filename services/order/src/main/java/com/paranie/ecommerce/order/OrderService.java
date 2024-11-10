package com.paranie.ecommerce.order;

import com.paranie.ecommerce.customer.CustomerClient;
import com.paranie.ecommerce.exception.BusinessException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public Integer createOrder(@Valid OrderRequest request) {
        // Check the customer --> OpenFeign
        var customer = this.customerClient
                .findCustomerById(request.customerId())
                .orElseThrow(
                        () -> new BusinessException("Cannot create order:: No customer exists with the provided ID:: " + request.customerId())
                );

        // Purchase the products --> product microservice (RestTemplate)

        // Persist order

        // persist order lines

        // start payment process

        // send order confirmation  --> notification microservice(kafka)

    }
}
