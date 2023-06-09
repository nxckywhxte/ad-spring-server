package ru.nxckywhxte.ad.spring.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nxckywhxte.ad.spring.server.dtos.UserDto;
import ru.nxckywhxte.ad.spring.server.services.impl.UserServiceImpl;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserEntityController {
    private final UserServiceImpl userService;

    @PostMapping()
    public UserDto create(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping()
    public Collection<UserDto> getAllRoles() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getOneUserById(@PathVariable String userId) {
        return userService.getOneUserById(UUID.fromString(userId));
    }

    @GetMapping("/{username}")
    public UserDto getOneUserByUserName(@PathVariable String username) {

        return userService.getOneUserByUserName(username);
    }
}
