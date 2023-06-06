package ru.nxckywhxte.ad.spring.server.services;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nxckywhxte.ad.spring.server.dtos.RoleDto;
import ru.nxckywhxte.ad.spring.server.entities.RoleEntity;

import java.util.Collection;
import java.util.UUID;
@Service
public interface RoleService {
    public Collection<RoleDto> getAllRoles();

    public RoleDto getRoleById(UUID roleId);

    public RoleDto getRoleByRoleName(String roleName);

    public void removeRoleById(UUID roleId);

    public RoleDto createRole(RoleDto roleDto);
}
