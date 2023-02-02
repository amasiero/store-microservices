package com.andreymasiero.cart.services;

import com.andreymasiero.dtos.users.UserDto;
import com.andreymasiero.exceptions.users.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    public UserDto getUserBySocialId(String socialId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/user/social-id/" + socialId;
            ResponseEntity<UserDto> response = restTemplate.getForEntity(url, UserDto.class);
            return response.getBody();
        }catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException();
        }
    }
}
