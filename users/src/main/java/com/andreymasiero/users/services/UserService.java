package com.andreymasiero.users.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.andreymasiero.dtos.users.UserDto;
import com.andreymasiero.exceptions.users.UserNotFoundException;
import com.andreymasiero.users.converters.DtoConverter;
import com.andreymasiero.users.entities.User;
import com.andreymasiero.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();

        return users.stream()
            .map(DtoConverter::fromUser)
            .collect(Collectors.toList());
    }

    public UserDto findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(DtoConverter::fromUser).orElse(null);
    }

    public UserDto save(UserDto userDto) {
        userDto.setCreatedOn(LocalDate.now());
        User user = userRepository.save(User.from(userDto));
        return DtoConverter.fromUser(user);
    }

    public UserDto delete(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(userRepository::delete);
        return null;
    }

    public UserDto findBySocialId(String socialId) {
        Optional<User> user = userRepository.findBySocialId(socialId);
        return user.map(DtoConverter::fromUser).orElseThrow(UserNotFoundException::new);
    }

    public List<UserDto> queryByName(String name) {
        List<User> users = userRepository.queryByNameLike(name);
        return users.stream()
            .map(DtoConverter::fromUser)
            .collect(Collectors.toList());
    }
}
