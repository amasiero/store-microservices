package com.andreymasiero.cart.services;

import com.andreymasiero.dtos.products.ProductDto;
import com.andreymasiero.exceptions.products.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    @Value("${PRODUCT_API_URL:http://localhost:8081/product/}")
    private String productUrlApi;

    public ProductDto getProductByIdentifier(String productIdentifier) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = productUrlApi + productIdentifier;
            ResponseEntity<ProductDto> response = restTemplate.getForEntity(url, ProductDto.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProductNotFoundException();
        }
    }
}
