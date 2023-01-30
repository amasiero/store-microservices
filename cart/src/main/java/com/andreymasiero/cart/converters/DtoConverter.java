package com.andreymasiero.cart.converters;

import java.util.stream.Collectors;

import com.andreymasiero.cart.entities.Cart;
import com.andreymasiero.cart.entities.Item;
import com.andreymasiero.dtos.cart.CartDto;
import com.andreymasiero.dtos.cart.ItemDto;

public class DtoConverter {
    public static CartDto fromCart(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setUserIdentifier(cart.getUserIdentifier());
        cartDto.setDate(cart.getDate());
        cartDto.setTotal(cart.getTotal());
        cartDto.setItems(cart
            .getItems()
            .stream()
            .map(DtoConverter::fromItem)
            .collect(Collectors.toList()));
        return cartDto;
    }

    public static ItemDto fromItem(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setProductIdentifier(item.getProductIdentifier());
        itemDto.setPrice(item.getPrice());
        return itemDto;
    }
}
