package com.paranie.ecommerce.product;

public class ProductPurchaseException extends Throwable {
    public ProductPurchaseException(String someProductsAreNotAvailable) {
        super(someProductsAreNotAvailable);
    }
}
