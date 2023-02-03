package com.andreymasiero.products.controllers;

import com.andreymasiero.dtos.products.CategoryDto;
import com.andreymasiero.dtos.products.ProductDto;
import com.andreymasiero.products.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private ProductController productController;
    @MockBean
    private ProductService productService;

    private ProductDto productDto;
    @BeforeEach
    public void setup() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);

        productDto = new ProductDto();
        productDto.setName("Test Product");
        productDto.setProductIdentifier("test-product");
        productDto.setDescription("test product description");
        productDto.setPrice(100f);
        productDto.setCategoryDto(categoryDto);
    }

    @Test
    public void whenProductCreated_thenStatus201() throws Exception {
        mockMvc.perform(post("/products")
            .content(asJsonString(productDto))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void whenProductHasCategoryNull_thenStatus400() throws Exception {
        productDto.setCategoryDto(null);

        mockMvc.perform(post("/products")
                .content(asJsonString(productDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void whenProductDeletedSuccess_thenStatus204() throws Exception {
        mockMvc.perform(delete("/products/1"))
            .andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
