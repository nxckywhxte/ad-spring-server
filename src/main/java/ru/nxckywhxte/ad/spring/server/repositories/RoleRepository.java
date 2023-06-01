package ru.nxckywhxte.ad.spring.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nxckywhxte.ad.spring.server.entities.RoleEntity;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    RoleEntity findRoleEntityById(UUID roleId);

    RoleEntity findRoleEntityByName(String roleName);
}
