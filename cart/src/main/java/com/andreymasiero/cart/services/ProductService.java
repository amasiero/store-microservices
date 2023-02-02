package com.andreymasiero.cart.services;

import com.andreymasiero.dtos.products.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
    public ProductDto getProductByIdentifier(String productIdentifier) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/product/" + productIdentifier;
        ResponseEntity<ProductDto> response = restTemplate.getForEntity(url, ProductDto.class);
        return response.getBody();
    }
}
