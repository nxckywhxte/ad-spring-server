package ru.nxckywhxte.ad.spring.server.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nxckywhxte.ad.spring.server.dtos.LoginDto;
import ru.nxckywhxte.ad.spring.server.dtos.RegisterDto;
import ru.nxckywhxte.ad.spring.server.dtos.UserDto;
import ru.nxckywhxte.ad.spring.server.entities.TokenEntity;
import ru.nxckywhxte.ad.spring.server.entities.UserEntity;
import ru.nxckywhxte.ad.spring.server.enums.TokenType;
import ru.nxckywhxte.ad.spring.server.mappers.UserMapper;
import ru.nxckywhxte.ad.spring.server.repositories.TokenRepository;
import ru.nxckywhxte.ad.spring.server.repositories.UserRepository;
import ru.nxckywhxte.ad.spring.server.services.AuthService;
import ru.nxckywhxte.ad.spring.server.services.JwtService;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    @Override
    public RegisterDto register(UserDto userDto) {
        UserDto newUser = userService.createUser(userDto);
        UserEntity mappedUserEntity = userMapper.map(newUser);
        String jwtToken = jwtService.generateToken(mappedUserEntity);
        String refreshToken = jwtService.generateRefreshToken(mappedUserEntity);
        saveUserToken(mappedUserEntity, jwtToken);
        return RegisterDto.builder()
                .email(mappedUserEntity.getEmail())
                .roles(mappedUserEntity.getRoles())
                .username(mappedUserEntity.getUsername())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public RegisterDto login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getRawPassword()
                )
        );
        UserDto existUser = userService.getOneUserByUserName(loginDto.getUsername());
        UserEntity mappedUserEntity = userMapper.map(existUser);
        String jwtToken = jwtService.generateToken(mappedUserEntity);
        String refreshToken = jwtService.generateRefreshToken(mappedUserEntity);
        revokeAllUserTokens(mappedUserEntity);
        saveUserToken(mappedUserEntity, jwtToken);
        return RegisterDto.builder()
                .email(mappedUserEntity.getEmail())
                .roles(mappedUserEntity.getRoles())
                .username(mappedUserEntity.getUsername())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (Objects.nonNull(username)) {
            UserEntity user = this.userRepository.findByUsername(username)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                RegisterDto authResponse = RegisterDto.builder()
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .roles(user.getRoles())
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(),authResponse);
            }
        }
    }

    private void saveUserToken(UserEntity user, String jwtToken) {
        var token = TokenEntity.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(UserEntity user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
