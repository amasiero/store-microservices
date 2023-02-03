package com.andreymasiero.cart.controllers;

import java.util.List;

import com.andreymasiero.cart.services.CartService;
import com.andreymasiero.dtos.cart.CartDto;
import com.andreymasiero.dtos.cart.ItemDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private CartController cartController;
    @MockBean
    private CartService cartService;

    @Test
    public void whenCartCreated_thenStatus201() throws Exception {
        CartDto cartDto = getCartDto();
        mockMvc.perform(post("/cart")
                .content(asJsonString(cartDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void whenUserIdentifierIsNull_thenStatus400() throws Exception {
        CartDto cartDto = getCartDto();
        cartDto.setUserIdentifier(null);
        mockMvc.perform(post("/cart")
                .content(asJsonString(cartDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void whenItemsIsNull_thenStatus400() throws Exception {
        CartDto cartDto = getCartDto();
        cartDto.setItems(null);
        mockMvc.perform(post("/cart")
                .content(asJsonString(cartDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    public static CartDto getCartDto() {
        CartDto cartDto = new CartDto();
        cartDto.setUserIdentifier("test-user");
        cartDto.setItems(List.of(getItemDto()));
        return cartDto;
    }

    public static ItemDto getItemDto() {
        ItemDto itemDto = new ItemDto();
        itemDto.setProductIdentifier("123");
        itemDto.setPrice(100F);
        return itemDto;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
