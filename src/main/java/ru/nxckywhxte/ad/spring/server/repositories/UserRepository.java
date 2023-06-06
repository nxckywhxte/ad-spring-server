package ru.nxckywhxte.ad.spring.server.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nxckywhxte.ad.spring.server.entities.UserEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findUserEntityById(UUID userId);

    UserEntity findUserEntityByUsername(String username);

    Optional<UserEntity> findByUsername(String username);
}
