package com.mgmetehan.pessimisticOptimisticLocking;

import com.mgmetehan.pessimisticOptimisticLocking.model.Product;
import com.mgmetehan.pessimisticOptimisticLocking.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class PessimisticOptimisticLockingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PessimisticOptimisticLockingApplication.class, args);
    }

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        var products = Arrays.asList(
                Product.builder()
                        .name("Product A")
                        .price(BigDecimal.valueOf(100.00))
                        .stock(1000L)
                        .build(),
                Product.builder()
                        .name("Product B")
                        .price(BigDecimal.valueOf(200.00))
                        .stock(1000L)
                        .build(),
                Product.builder()
                        .name("Product C")
                        .price(BigDecimal.valueOf(300.00))
                        .stock(1000L)
                        .build()
        );

        productRepository.saveAll(products);
    }
}

