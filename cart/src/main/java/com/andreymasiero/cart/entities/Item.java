package com.andreymasiero.cart.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.andreymasiero.dtos.cart.ItemDto;

@Embeddable
public class Item {

    @Column(name = "product_identifier")
    private String productIdentifier;
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

    public static Item from(ItemDto itemDto) {
        Item item = new Item();
        item.setProductIdentifier(itemDto.getProductIdentifier());
        item.setPrice(itemDto.getPrice());
        return item;
    }
}
