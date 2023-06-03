package ru.nxckywhxte.ad.spring.server.services;

import ru.nxckywhxte.ad.spring.server.entities.UserEntity;

import java.util.Collection;
import java.util.UUID;

public interface UserService {
    public Collection<UserEntity> getAllUsers();
    public UserEntity getOneUserById(UUID userId);
    public UserEntity getOneUserByUserName(String username);
    public UserEntity getOneUserByRoleName(String username, String roleName);
    public Collection<UserEntity> getAllUsersByRoleName(String roleName);
}
