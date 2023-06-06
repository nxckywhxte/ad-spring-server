package ru.nxckywhxte.ad.spring.server.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nxckywhxte.ad.spring.server.entities.GroupEntity;

import java.util.UUID;

@Repository
@Transactional
public interface GroupRepository extends JpaRepository<GroupEntity, UUID> {
    GroupEntity findGroupEntityById(UUID groupId);
    GroupEntity findGroupEntityByName(String groupName);
}
