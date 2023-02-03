package com.andreymasiero.cart.controllers;

import javax.validation.Valid;

import java.time.LocalDate;
import java.util.List;

import com.andreymasiero.cart.dtos.CartReportDto;
import com.andreymasiero.cart.services.CartService;
import com.andreymasiero.dtos.cart.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(
        final CartService cartService
    ) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getCarts() {
        List<CartDto> carts = cartService.getAll();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/by-user/{userIdentifier}")
    public ResponseEntity<List<CartDto>> getCarts(@PathVariable String userIdentifier) {
        List<CartDto> carts = cartService.getByUser(userIdentifier);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<CartDto>> getCarts(@RequestBody CartDto cartDto) {
        List<CartDto> carts = cartService.getByDate(cartDto);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> findById(@PathVariable Long id) {
        CartDto cart = cartService.findById(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CartDto>> getCartsByFilter(
        @RequestParam(name = "begin")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate begin,
        @RequestParam(name = "end", required = false)
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate end,
        @RequestParam(name = "minimum", required = false)
        Float minimum
    ) {
      List<CartDto> carts = cartService.getCartByFilter(begin, end, minimum);
      return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<CartReportDto> getReportByDate(
        @RequestParam(name = "begin")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate begin,
        @RequestParam(name = "end")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate end
    ) {
        CartReportDto report = cartService.getReportByDate(begin, end);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartDto> save(@Valid @RequestBody CartDto cartDto) {
        CartDto cart = cartService.save(cartDto);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }
}
