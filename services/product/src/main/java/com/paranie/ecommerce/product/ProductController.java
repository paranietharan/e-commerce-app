package com.paranie.ecommerce.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(productService.getAllproducts());
    }
}
