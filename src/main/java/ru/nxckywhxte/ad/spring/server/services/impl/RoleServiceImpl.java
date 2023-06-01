package ru.nxckywhxte.ad.spring.server.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.nxckywhxte.ad.spring.server.dtos.RoleDto;
import ru.nxckywhxte.ad.spring.server.entities.RoleEntity;
import ru.nxckywhxte.ad.spring.server.repositories.RoleRepository;
import ru.nxckywhxte.ad.spring.server.services.RoleService;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Collection<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity getRoleById(UUID roleId) {
        RoleEntity existRole = roleRepository.findRoleEntityById(roleId);
        if (Objects.equals(existRole, null)) {
            throw new ResponseStatusException(NOT_FOUND, "Роль с такими данными не найдена! Проверьте данные и попробуйте еще раз.");
        }
        return existRole;
    }

    @Override
    public RoleEntity getRoleByRoleName(String roleName) {
        RoleEntity existRole = roleRepository.findRoleEntityByName(roleName);
        if (Objects.equals(existRole, null)) {
            throw new ResponseStatusException(NOT_FOUND, "Роль с такими данными не найдена! Проверьте данные и попробуйте еще раз.");
        }
        return existRole;
    }

    @Override
    public void removeRoleById(UUID roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public RoleEntity createRole(RoleEntity roleEntity) {
        RoleEntity existRole = roleRepository.findRoleEntityByName(roleEntity.getName());
        if (Objects.nonNull(existRole)) {
            throw new ResponseStatusException(CONFLICT, "Роль с такими данными уже существует! Проверьте данные и попробуйте еще раз.");
        }
        RoleEntity newRole = RoleEntity.builder()
                .name(roleEntity.getName())
                .build();
        roleRepository.saveAndFlush(newRole);
        return newRole;
    }
}
