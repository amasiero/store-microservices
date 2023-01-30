package com.andreymasiero.products.converters;

import com.andreymasiero.dtos.products.CategoryDto;
import com.andreymasiero.dtos.products.ProductDto;
import com.andreymasiero.products.entities.Category;
import com.andreymasiero.products.entities.Product;

public class DtoConverter {

    public static CategoryDto fromCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    public static ProductDto fromProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setProductIdentifier(product.getProductIdentifier());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            productDto.setCategoryDto(DtoConverter.fromCategory(product.getCategory()));
        }
        return productDto;
    }
}
