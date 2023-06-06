package ru.nxckywhxte.ad.spring.server.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.nxckywhxte.ad.spring.server.dtos.UserDto;
import ru.nxckywhxte.ad.spring.server.entities.UserEntity;
import ru.nxckywhxte.ad.spring.server.mappers.UserMapper;
import ru.nxckywhxte.ad.spring.server.repositories.UserRepository;
import ru.nxckywhxte.ad.spring.server.services.UserService;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Collection<UserDto> getAllUsers() {
        Collection<UserEntity> allUsers = userRepository.findAll();
        return allUsers.stream().map(userMapper::map).toList();
    }

    @Override
    public UserDto getOneUserById(UUID userId) {
        UserEntity existsUser = userRepository.findUserEntityById(userId);
        if (Objects.isNull(existsUser) || !Objects.equals(existsUser.getId(), userId)) {
            throw new ResponseStatusException(NOT_FOUND, "Роль с такими данными не найдена! Проверьте данные и попробуйте еще раз.");
        }
        return userMapper.map(existsUser);
    }

    @Override
    public UserDto getOneUserByUserName(String username) {
        UserEntity existsUser = userRepository.findUserEntityByUsername(username);
        if (Objects.isNull(existsUser) || !Objects.equals(existsUser.getUsername(), username)) {
            throw new ResponseStatusException(NOT_FOUND, "Роль с такими данными не найдена! Проверьте данные и попробуйте еще раз.");
        }
        return userMapper.map(existsUser);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity existUser = userRepository.findUserEntityByUsername(userDto.getUsername());
        if (Objects.nonNull(existUser)) {
            throw new ResponseStatusException(CONFLICT, "Пользователь с такими данными уже существует! Проверьте данные и попробуйте еще раз.");
        }
        UserEntity newUser = UserEntity.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .groups(userDto.getGroups())
                .roles(userDto.getRoles())
                .hashedPassword(userDto.getRawPassword())
                .build();
        userRepository.saveAndFlush(newUser);
        return userMapper.map(newUser);
    }
}
