package com.andreymasiero.cart.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.andreymasiero.cart.entities.Item;

public class ItemDto {

    @NotBlank
    private String productIdentifier;
    @NotNull
    private Float price;

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public static ItemDto from(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setProductIdentifier(item.getProductIdentifier());
        itemDto.setPrice(item.getPrice());
        return itemDto;
    }
}
