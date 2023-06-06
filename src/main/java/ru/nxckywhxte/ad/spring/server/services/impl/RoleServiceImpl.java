package ru.nxckywhxte.ad.spring.server.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.nxckywhxte.ad.spring.server.dtos.RoleDto;
import ru.nxckywhxte.ad.spring.server.entities.RoleEntity;
import ru.nxckywhxte.ad.spring.server.mappers.RoleMapper;
import ru.nxckywhxte.ad.spring.server.repositories.RoleRepository;
import ru.nxckywhxte.ad.spring.server.services.RoleService;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Collection<RoleDto> getAllRoles() {
        Collection<RoleEntity> allRoles = roleRepository.findAll();
        return allRoles.stream().map(roleMapper::map).toList();
    }

    @Override
    public RoleDto getRoleById(UUID roleId) {
        RoleEntity existRole = roleRepository.findRoleEntityById(roleId);
        if (Objects.equals(existRole, null)) {
            throw new ResponseStatusException(NOT_FOUND, "Роль с такими данными не найдена! Проверьте данные и попробуйте еще раз.");
        }
        return roleMapper.map(existRole);
    }

    @Override
    public RoleDto getRoleByRoleName(String roleName) {
        RoleEntity existRole = roleRepository.findRoleEntityByName(roleName);
        if (Objects.equals(existRole, null)) {
            throw new ResponseStatusException(NOT_FOUND, "Роль с такими данными не найдена! Проверьте данные и попробуйте еще раз.");
        }
        return roleMapper.map(existRole);
    }

    @Override
    public void removeRoleById(UUID roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        RoleEntity existRole = roleRepository.findRoleEntityByName(roleDto.getName());
        if (Objects.nonNull(existRole)) {
            throw new ResponseStatusException(CONFLICT, "Роль с такими данными уже существует! Проверьте данные и попробуйте еще раз.");
        }
        RoleEntity newRole = RoleEntity.builder()
                .name(roleDto.getName())
                .build();
        roleRepository.saveAndFlush(newRole);
        return roleMapper.map(newRole);
    }
}
