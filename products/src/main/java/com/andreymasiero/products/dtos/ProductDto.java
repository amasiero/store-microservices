package com.andreymasiero.products.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.andreymasiero.products.entities.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String productIdentifier;
    @NotNull
    private Float price;
    @NotNull
    @JsonProperty("category")
    private CategoryDto categoryDto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    public static ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setProductIdentifier(product.getProductIdentifier());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            productDto.setCategoryDto(CategoryDto.from(product.getCategory()));
        }
        return productDto;
    }
}
