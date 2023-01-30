package com.andreymasiero.cart.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.andreymasiero.cart.converters.DtoConverter;
import com.andreymasiero.cart.dtos.CartReportDto;
import com.andreymasiero.cart.entities.Cart;
import com.andreymasiero.cart.repositories.CartRepository;
import com.andreymasiero.dtos.cart.CartDto;
import com.andreymasiero.dtos.cart.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(
        final CartRepository cartRepository
    ) {
        this.cartRepository = cartRepository;
    }

    public List<CartDto> getAll() {
        return cartRepository.findAll()
            .stream()
            .map(DtoConverter::fromCart)
            .collect(Collectors.toList());
    }

    public List<CartDto> getByUser(String userIdentifier) {
        return cartRepository.findAllByUserIdentifier(userIdentifier)
            .stream()
            .map(DtoConverter::fromCart)
            .collect(Collectors.toList());
    }

    public List<CartDto> getByDate(CartDto cartDto) {
        return cartRepository.findAllByDateGreaterThanEqual(cartDto.getDate())
            .stream()
            .map(DtoConverter::fromCart)
            .collect(Collectors.toList());
    }

    public CartDto findById(Long productId) {
        Optional<Cart> cart = cartRepository.findById(productId);
        return cart.map(DtoConverter::fromCart).orElse(null);
    }

    public CartDto save(CartDto cartDto) {
        cartDto.setTotal(cartDto.getItems()
            .stream()
            .map(ItemDto::getPrice)
            .reduce(0.f, Float::sum));

        cartDto.setDate(LocalDate.now());
        Cart cart = cartRepository.save(Cart.from(cartDto));
        return DtoConverter.fromCart(cart);
    }

    public List<CartDto> getCartByFilter(LocalDate begin, LocalDate end, Float minimum) {
        List<Cart> carts = cartRepository.getShopByFilters(begin, end, minimum);
        return carts
            .stream()
            .map(DtoConverter::fromCart)
            .collect(Collectors.toList());
    }

    public CartReportDto getReportByDate(LocalDate begin, LocalDate end) {
        return cartRepository.getReportByDate(begin, end);
    }
}
