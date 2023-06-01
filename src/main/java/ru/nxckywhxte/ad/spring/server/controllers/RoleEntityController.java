package ru.nxckywhxte.ad.spring.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nxckywhxte.ad.spring.server.dtos.RoleDto;
import ru.nxckywhxte.ad.spring.server.entities.RoleEntity;
import ru.nxckywhxte.ad.spring.server.mappers.RoleMapper;
import ru.nxckywhxte.ad.spring.server.services.impl.RoleServiceImpl;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleEntityController {
    private final RoleMapper roleMapper;
    private final RoleServiceImpl roleService;

    @PostMapping()
    public RoleDto createRole(@RequestBody RoleDto roleDto) {
        RoleEntity roleEntity = roleMapper.map(roleDto);
        RoleEntity newRoleEntity = roleService.createRole(roleEntity);
        return roleMapper.map(newRoleEntity);
    }

    @GetMapping()
    public Collection<RoleDto> getAllRoles() {
        Collection<RoleEntity> allRoles = roleService.getAllRoles();
        return allRoles.stream().map(roleMapper::map).toList();
    }

    @GetMapping(value = "/{roleId}")
    public RoleDto getRoleById(@PathVariable String roleId) {
        RoleEntity roleEntity = roleService.getRoleById(UUID.fromString(roleId));
        return roleMapper.map(roleEntity);
    }

    @DeleteMapping(value = "/{roleId}")
    public void deleteRole(@PathVariable String roleId) {
        roleService.removeRoleById(UUID.fromString(roleId));
    }
}
