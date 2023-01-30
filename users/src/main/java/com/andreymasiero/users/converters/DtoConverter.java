package com.andreymasiero.users.converters;

import com.andreymasiero.dtos.users.UserDto;
import com.andreymasiero.users.entities.User;

public class DtoConverter {
    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setSocialId(user.getSocialId());
        userDto.setAddress(user.getAddress());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setCreatedOn(user.getCreatedOn());
        return userDto;
    }
}
