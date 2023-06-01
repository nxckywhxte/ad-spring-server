package ru.nxckywhxte.ad.spring.server.services;


import org.springframework.http.ResponseEntity;
import ru.nxckywhxte.ad.spring.server.dtos.RoleDto;
import ru.nxckywhxte.ad.spring.server.entities.RoleEntity;

import java.util.Collection;
import java.util.UUID;

public interface RoleService {
    public Collection<RoleEntity> getAllRoles();

    public RoleEntity getRoleById(UUID roleId);

    public RoleEntity getRoleByRoleName(String roleName);

    public void removeRoleById(UUID roleId);

    public RoleEntity createRole(RoleEntity roleEntity);
}
