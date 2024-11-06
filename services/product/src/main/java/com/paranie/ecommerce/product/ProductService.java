package com.paranie.ecommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(@Valid ProductRequest productRequest) {

        var product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productPurchaseRequests) throws ProductPurchaseException {
        var productIds = productPurchaseRequests
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        var storedProducts = productRepository.findAllByIdIn(productIds);

        if(productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("Some products are not available");
        }

        var storesRequest = productPurchaseRequests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {
            var storedProduct = storedProducts.get(i);
            var productPurchaseRequest = storesRequest.get(i);

            if(storedProduct.getAvilableQuantity() < productPurchaseRequest.quantity()) {
                throw new ProductPurchaseException("Product with ID " + storedProduct.getId() + " is not available in the requested quantity");
            }

            storedProduct.setAvilableQuantity(storedProduct.getAvilableQuantity() - productPurchaseRequest.quantity());
            productRepository.save(storedProduct);

            purchasedProducts.add(
                    new ProductPurchaseResponse(
                            storedProduct.getId(),
                            storedProduct.getName(),
                            storedProduct.getDescription(),
                            productPurchaseRequest.quantity(),
                            storedProduct.getPrice()
                    )
            );
        }

        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(
                        () -> new RuntimeException("Product not found with the ID:: " + productId)
                );
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
