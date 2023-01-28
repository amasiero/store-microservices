package com.andreymasiero.cart.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.andreymasiero.cart.entities.Cart;

public class CartDto {
    @NotBlank
    private String userIdentifier;
    @NotNull
    private Float total;
    @NotNull
    private LocalDate date;
    @NotNull
    private List<ItemDto> items;

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    public static CartDto from(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setUserIdentifier(cart.getUserIdentifier());
        cartDto.setDate(cart.getDate());
        cartDto.setTotal(cart.getTotal());
        cartDto.setItems(cart
            .getItems()
            .stream()
            .map(ItemDto::from)
            .collect(Collectors.toList()));
        return cartDto;
    }
}
