package com.andreymasiero.products.services;

import java.util.List;
import java.util.stream.Collectors;

import com.andreymasiero.products.dtos.ProductDto;
import com.andreymasiero.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(
        final ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll()
            .stream()
            .map(ProductDto::from)
            .collect(Collectors.toList());
    }

    public List<ProductDto> getProductByCategoryId(Long categoryId) {
        return productRepository.getProductByCategory(categoryId)
            .stream()
            .map(ProductDto::from)
            .collect(Collectors.toList());
    }
}
