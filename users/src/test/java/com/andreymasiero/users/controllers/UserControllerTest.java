package com.andreymasiero.users.controllers;

import com.andreymasiero.dtos.users.UserDto;
import com.andreymasiero.users.services.UserService;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private UserController userController;
    @MockBean
    private UserService userService;
    public UserDto userDto;

    @BeforeEach
    public void setup() {
        userDto = new UserDto();
        userDto.setName("test");
        userDto.setSocialId("123");
        userDto.setAddress("test avenue");
        userDto.setPhoneNumber("1234-5678");
        userDto.setEmail("test@email.com");
    }

    @Test
    public void whenUserSaveWithSuccess_thenStatus201() throws Exception {
        mockMvc.perform(post("/users")
            .content(asJsonString(userDto))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void whenUserDeleteWithSuccess_thenStatus204() throws Exception {
        mockMvc.perform(delete("/users/1"))
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
