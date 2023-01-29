package com.andreymasiero.cart.repositories;

import java.time.LocalDate;
import java.util.List;

import com.andreymasiero.cart.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>, ReportRepository {
    List<Cart> findAllByUserIdentifier(String userIdentifier);
    List<Cart> findAllByTotalGreaterThan(Float total);
    List<Cart> findAllByDateGreaterThanEqual(LocalDate date);
}
