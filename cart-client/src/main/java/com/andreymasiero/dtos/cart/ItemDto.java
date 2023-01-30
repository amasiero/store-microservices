package com.andreymasiero.dtos.cart;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
}
