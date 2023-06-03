package ru.nxckywhxte.ad.spring.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nxckywhxte.ad.spring.server.entities.GroupEntity;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<GroupEntity, UUID> {
    GroupEntity findGroupEntityById(UUID groupId);
    GroupEntity findGroupEntityByName(String groupName);
}
