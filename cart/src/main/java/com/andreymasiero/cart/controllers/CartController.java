package com.andreymasiero.cart.controllers;

import javax.validation.Valid;

import java.time.LocalDate;
import java.util.List;

import com.andreymasiero.cart.dtos.CartReportDto;
import com.andreymasiero.cart.services.CartService;
import com.andreymasiero.dtos.cart.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(
        final CartService cartService
    ) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public List<CartDto> getCarts() {
        return cartService.getAll();
    }

    @GetMapping("/cart/by-user/{userIdentifier}")
    public List<CartDto> getCarts(@PathVariable String userIdentifier) {
        return cartService.getByUser(userIdentifier);
    }

    @GetMapping("/cart/by-date")
    public List<CartDto> getCarts(@RequestBody CartDto cartDto) {
        return cartService.getByDate(cartDto);
    }

    @GetMapping("/cart/{id}")
    public CartDto findById(@PathVariable Long id) {
        return cartService.findById(id);
    }

    @GetMapping("/cart/search")
    public List<CartDto> getCartsByFilter(
        @RequestParam(name = "begin")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate begin,
        @RequestParam(name = "end", required = false)
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate end,
        @RequestParam(name = "minimum", required = false)
        Float minimum
    ) {
      return cartService.getCartByFilter(begin, end, minimum);
    }

    @GetMapping("/cart/report")
    public CartReportDto getReportByDate(
        @RequestParam(name = "begin")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate begin,
        @RequestParam(name = "end")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate end
    ) {
        return cartService.getReportByDate(begin, end);
    }

    @PostMapping("/cart")
    public CartDto save(@Valid @RequestBody CartDto cartDto) {
        return cartService.save(cartDto);
    }
}
