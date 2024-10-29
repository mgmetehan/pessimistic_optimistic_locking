package com.mgmetehan.pessimisticOptimisticLocking.controller;

import com.mgmetehan.pessimisticOptimisticLocking.model.Product;
import com.mgmetehan.pessimisticOptimisticLocking.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/{id}/reduce-stock")
    public ResponseEntity<String> reduceStock(@PathVariable Long id) {
        boolean isReduced = productService.reduceStock(id);
        if (isReduced) {
            return ResponseEntity.ok("Stock reduced successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found or out of stock.");
        }
    }

    @GetMapping("/pessimistic/{id}")
    public ResponseEntity<Product> getProductWithPessimisticLock(@PathVariable Long id) {
        Product product = productService.getProductWithPessimisticLock(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/optimistic/{id}")
    public ResponseEntity<Product> getProductWithOptimisticLock(@PathVariable Long id) {
        Product product = productService.getProductWithOptimisticLock(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}/{stock}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @PathVariable Long stock) {
        Product updatedProduct = productService.updateProduct(id, stock);
        return ResponseEntity.ok(updatedProduct);
    }

    @PutMapping("/pessimistic/{id}/{stock}")
    public ResponseEntity<Product> updateProductWithPessimisticLock(@PathVariable Long id, @PathVariable Long stock) {
        Product updatedProduct = productService.updateProductWithPessimisticLock(id, stock);
        return ResponseEntity.ok(updatedProduct);
    }
}
