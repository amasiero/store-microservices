package com.andreymasiero.cart.repositories;

import java.time.LocalDate;
import java.util.List;

import com.andreymasiero.cart.dtos.CartReportDto;
import com.andreymasiero.cart.entities.Cart;

public interface ReportRepository {
    List<Cart> getShopByFilters(LocalDate begin, LocalDate end, Float minimum);
    CartReportDto getReportByDate(LocalDate begin, LocalDate end);
}
