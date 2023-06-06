package ru.nxckywhxte.ad.spring.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nxckywhxte.ad.spring.server.dtos.RoleDto;
import ru.nxckywhxte.ad.spring.server.entities.RoleEntity;
import ru.nxckywhxte.ad.spring.server.mappers.RoleMapper;
import ru.nxckywhxte.ad.spring.server.services.RoleService;
import ru.nxckywhxte.ad.spring.server.services.impl.RoleServiceImpl;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleEntityController {
    private final RoleService roleService;

    @PostMapping()
    public RoleDto createRole(@RequestBody RoleDto roleDto) {
        return roleService.createRole(roleDto);
    }

    @GetMapping()
    public Collection<RoleDto> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping(value = "/{roleId}")
    public RoleDto getRoleById(@PathVariable String roleId) {
        return roleService.getRoleById(UUID.fromString(roleId));
    }

    @DeleteMapping(value = "/{roleId}")
    public void deleteRole(@PathVariable String roleId) {
        roleService.removeRoleById(UUID.fromString(roleId));
    }
}
