package com.andreymasiero.cart.services;

import java.util.List;

import com.andreymasiero.cart.entities.Cart;
import com.andreymasiero.cart.repositories.CartRepository;
import com.andreymasiero.dtos.cart.CartDto;
import com.andreymasiero.dtos.cart.ItemDto;
import com.andreymasiero.dtos.products.ProductDto;
import com.andreymasiero.dtos.users.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @InjectMocks
    private CartService cartService;
    @Mock
    private UserService userService;
    @Mock
    private ProductService productService;
    @Mock
    private CartRepository cartRepository;

    @BeforeEach
    public void setup() {
        when(userService.getUserBySocialId(any())).thenReturn(new UserDto());
        when(productService.getProductByIdentifier("123"))
            .thenReturn(getProductDto());
        when(cartRepository.save(any()))
            .thenReturn(Cart.from(getCartDto()));
    }

    @Test
    public void whenCartCreated_thenSuccess() {
        CartDto cartDto = getCartDto();
        CartDto result = cartService.save(cartDto);
        Assertions.assertEquals(cartDto.getTotal(), result.getTotal());
        Assertions.assertEquals(cartDto.getItems().size(), result.getItems().size());
        verify(cartRepository, times(1)).save(any());
    }

    public static ProductDto getProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setProductIdentifier("123");
        productDto.setPrice(100F);
        return productDto;
    }

    public static CartDto getCartDto() {
        CartDto cartDto = new CartDto();
        cartDto.setUserIdentifier("123");
        cartDto.setItems(List.of(getItemDto()));
        cartDto.setTotal(100F);
        return cartDto;
    }

    public static ItemDto getItemDto() {
        ItemDto itemDto = new ItemDto();
        itemDto.setProductIdentifier("123");
        itemDto.setPrice(100F);
        return itemDto;
    }
}
