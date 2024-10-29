package com.mgmetehan.pessimisticOptimisticLocking.service;

import com.mgmetehan.pessimisticOptimisticLocking.model.Product;
import com.mgmetehan.pessimisticOptimisticLocking.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public Product getProductWithPessimisticLock(Long id) {
        return productRepository.findWithPessimisticLock(id);
    }

    public Product getProductWithOptimisticLock(Long id) {
        return productRepository.findWithOptimisticLock(id);
    }

    public Product updateProduct(Long id, Long stock) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStock(stock);
        return productRepository.save(product);
    }

    public Product updateProductWithPessimisticLock(Long id, Long stock) {
        productRepository.updateProductStock(id, stock);
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public boolean reduceStock(Long productId, String type) {
        Product product = null;
        if ("pessimistic".equals(type)) {
            product = productRepository.findWithPessimisticLock(productId);
        } else if ("optimistic".equals(type)) {
            product = productRepository.findWithOptimisticLock(productId);
        } else {
            product = productRepository.findById(productId).orElse(null);
        }

        if (product == null || product.getStock() <= 0) {
            return false;
        }

        product.setStock(product.getStock() - 1);
        productRepository.save(product);
        return true;
    }
}
