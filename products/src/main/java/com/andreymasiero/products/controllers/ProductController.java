package com.andreymasiero.products.controllers;

import javax.validation.Valid;

import java.util.List;

import com.andreymasiero.products.dtos.ProductDto;
import com.andreymasiero.products.exceptions.ProductNotFoundException;
import com.andreymasiero.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(
        final ProductService productService
    ) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDto> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/products/category/{categoryId}")
    public List<ProductDto> getProductByCategory(@PathVariable Long categoryId) {
        return productService.getProductByCategoryId(categoryId);
    }

    @GetMapping("/products/{productIdentifier}")
    public ProductDto findById(@PathVariable String productIdentifier) {
        return productService.findByProductIdentifier(productIdentifier);
    }

    @PostMapping("/products")
    public ProductDto save(@Valid @RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @DeleteMapping("/products/{id}")
    public ProductDto delete(@PathVariable Long id) throws ProductNotFoundException {
        return productService.delete(id);
    }
}
