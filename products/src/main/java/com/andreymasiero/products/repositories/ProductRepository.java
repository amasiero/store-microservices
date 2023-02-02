package com.andreymasiero.products.repositories;

import java.util.List;
import java.util.Optional;

import com.andreymasiero.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select p "
        + "from product p "
        + "join category c on p.category.id = c.id "
        + "where c.id = :categoryId")
    List<Product> getProductByCategory(@Param("categoryId") Long categoryId);

    Optional<Product> findByProductIdentifier(String productIdentifier);
}
