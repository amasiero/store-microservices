package com.andreymasiero.products.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.andreymasiero.dtos.products.ProductDto;
import com.andreymasiero.exceptions.products.CategoryNotFoundException;
import com.andreymasiero.exceptions.products.ProductNotFoundException;
import com.andreymasiero.products.converters.DtoConverter;
import com.andreymasiero.products.entities.Product;
import com.andreymasiero.products.repositories.CategoryRepository;
import com.andreymasiero.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(
        final ProductRepository productRepository,
        final CategoryRepository categoryRepository
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll()
            .stream()
            .map(DtoConverter::fromProduct)
            .collect(Collectors.toList());
    }

    public List<ProductDto> getProductByCategoryId(Long categoryId) {
        return productRepository.getProductByCategory(categoryId)
            .stream()
            .map(DtoConverter::fromProduct)
            .collect(Collectors.toList());
    }

    public ProductDto findByProductIdentifier(String productIdentifier) {
        Optional<Product> product = productRepository.findByProductIdentifier(productIdentifier);
        return product.map(DtoConverter::fromProduct).orElseThrow(ProductNotFoundException::new);
    }

    public ProductDto save(ProductDto productDto) {
        boolean existsCategory = categoryRepository
            .existsById(productDto.getCategoryDto().getId());
        if (!existsCategory) {
            throw new CategoryNotFoundException();
        }
        Product product = productRepository.save(Product.from(productDto));
        return DtoConverter.fromProduct(product);
    }

    public ProductDto delete(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.map(p -> {
            productRepository.delete(p);
            return DtoConverter.fromProduct(p);
        }).orElseThrow(ProductNotFoundException::new);
    }
}
