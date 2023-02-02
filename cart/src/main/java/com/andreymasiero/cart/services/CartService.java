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
import com.andreymasiero.dtos.products.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public CartService(
        final CartRepository cartRepository,
        final ProductService productService,
        final UserService userService
    ) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.userService = userService;
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
        if (userService.getUserBySocialId(cartDto.getUserIdentifier()) == null) {
            return null;
        }

        if (!validateProducts(cartDto.getItems())) {
            return null;
        }

        cartDto.setTotal(cartDto.getItems()
            .stream()
            .map(ItemDto::getPrice)
            .reduce(0.f, Float::sum));

        cartDto.setDate(LocalDate.now());
        Cart cart = cartRepository.save(Cart.from(cartDto));
        return DtoConverter.fromCart(cart);
    }

    private boolean validateProducts(List<ItemDto> items) {
        for(ItemDto item : items) {
            ProductDto productDto = productService
                .getProductByIdentifier(item.getProductIdentifier());
            if (productDto == null) {
                return false;
            }
            item.setPrice(productDto.getPrice());
        }
        return true;
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
