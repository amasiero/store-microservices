package com.andreymasiero.products.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.andreymasiero.dtos.products.ProductDto;
import com.andreymasiero.products.converters.DtoConverter;
import com.andreymasiero.products.entities.Product;
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
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        return product != null ? DtoConverter.fromProduct(product) : null;
    }

    public ProductDto save(ProductDto productDto) {
        Product product = productRepository.save(Product.from(productDto));
        return DtoConverter.fromProduct(product);
    }

    public ProductDto delete(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return DtoConverter.fromProduct(product.get());
        }
        return null;
    }
}
