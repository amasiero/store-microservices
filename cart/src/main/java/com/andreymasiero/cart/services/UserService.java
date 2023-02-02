package com.andreymasiero.cart.services;

import com.andreymasiero.dtos.users.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    public UserDto getUserBySocialId(String socialId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/social-id/" + socialId;
        ResponseEntity<UserDto> response = restTemplate.getForEntity(url, UserDto.class);
        return response.getBody();
    }
}
