package ru.nxckywhxte.ad.spring.server.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nxckywhxte.ad.spring.server.dtos.LoginDto;
import ru.nxckywhxte.ad.spring.server.dtos.RegisterDto;
import ru.nxckywhxte.ad.spring.server.dtos.UserDto;
import ru.nxckywhxte.ad.spring.server.services.AuthService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<RegisterDto> register(
            @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(service.register(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<RegisterDto> login(
            @RequestBody LoginDto loginDto
    ) {
        return ResponseEntity.ok(service.login(loginDto));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}
