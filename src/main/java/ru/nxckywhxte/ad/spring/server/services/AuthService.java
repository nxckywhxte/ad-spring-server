package ru.nxckywhxte.ad.spring.server.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.nxckywhxte.ad.spring.server.dtos.LoginDto;
import ru.nxckywhxte.ad.spring.server.dtos.RegisterDto;
import ru.nxckywhxte.ad.spring.server.dtos.UserDto;

import java.io.IOException;

public interface AuthService {
    public RegisterDto register(UserDto userDto);
    public RegisterDto login(LoginDto loginDto);

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;
}
