package com.mgmetehan.pessimisticOptimisticLocking.repository;

import com.mgmetehan.pessimisticOptimisticLocking.model.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE) // Pessimistic Lock
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Product findWithPessimisticLock(Long id);

    @Modifying
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("UPDATE Product p SET p.stock = :stock WHERE p.id = :id")
    void updateProductStock(@Param("id") Long id, @Param("stock") Long stock);

    @Lock(LockModeType.OPTIMISTIC) // Optimistic Lock
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Product findWithOptimisticLock(Long id);
}
