package ru.nxckywhxte.ad.spring.server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nxckywhxte.ad.spring.server.dtos.RoleDto;
import ru.nxckywhxte.ad.spring.server.dtos.UserDto;
import ru.nxckywhxte.ad.spring.server.entities.UserEntity;

import java.util.Collection;
import java.util.UUID;

@Service
public interface UserService {
    public Collection<UserDto> getAllUsers();
    public UserDto getOneUserById(UUID userId);
    public UserDto getOneUserByUserName(String username);

    public UserDto createUser(UserDto userDto);
}
