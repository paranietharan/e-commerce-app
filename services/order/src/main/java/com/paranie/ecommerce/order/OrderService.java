package com.paranie.ecommerce.order;

import com.paranie.ecommerce.customer.CustomerClient;
import com.paranie.ecommerce.exception.BusinessException;
import com.paranie.ecommerce.orderline.OrderLineRequest;
import com.paranie.ecommerce.orderline.OrderLineService;
import com.paranie.ecommerce.product.ProductClient;
import com.paranie.ecommerce.product.PurchaseRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(@Valid OrderRequest request) {
        // Check the customer --> OpenFeign
        var customer = this.customerClient
                .findCustomerById(request.customerId())
                .orElseThrow(
                        () -> new BusinessException("Cannot create order:: No customer exists with the provided ID:: " + request.customerId())
                );

        // Purchase the products --> product microservice (RestTemplate)
        this.productClient.purchasePrdouct(request.products());

        var order = this.repository.save(
                mapper.toOrder(request)
        );

        for(PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // TODO: Start payment process
        // Send the order confirmation --> notification-ms(Kafka)
    }
}
