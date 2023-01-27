package com.andreymasiero.users.controllers;

import java.util.List;

import com.andreymasiero.users.dtos.UserDto;
import com.andreymasiero.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/users/social-id/{socialId}")
    public UserDto delete(@PathVariable String socialId) {
        return userService.findBySocialId(socialId);
    }

    @GetMapping("/users/search")
    public List<UserDto> queryByName(
        @RequestParam(name="name", required = true)
        String name
    ) {
        return userService.queryByName(name);
    }

    @PostMapping("/users")
    public UserDto create(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @DeleteMapping("/users/{id}")
    UserDto delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
