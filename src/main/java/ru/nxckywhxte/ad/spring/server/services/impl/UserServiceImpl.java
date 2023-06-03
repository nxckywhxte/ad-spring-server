package ru.nxckywhxte.ad.spring.server.services.impl;

import ru.nxckywhxte.ad.spring.server.entities.UserEntity;
import ru.nxckywhxte.ad.spring.server.services.UserService;

import java.util.Collection;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public Collection<UserEntity> getAllUsers() {
        return null;
    }

    @Override
    public UserEntity getOneUserById(UUID userId) {
        return null;
    }

    @Override
    public UserEntity getOneUserByUserName(String username) {
        return null;
    }

    @Override
    public UserEntity getOneUserByRoleName(String username, String roleName) {
        return null;
    }

    @Override
    public Collection<UserEntity> getAllUsersByRoleName(String roleName) {
        return null;
    }
}
