package ru.nxckywhxte.ad.spring.server.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nxckywhxte.ad.spring.server.entities.RoleEntity;

import java.util.UUID;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    RoleEntity findRoleEntityById(UUID roleId);

    RoleEntity findRoleEntityByName(String roleName);
}
