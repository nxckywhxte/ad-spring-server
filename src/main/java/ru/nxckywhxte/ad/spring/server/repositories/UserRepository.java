package ru.nxckywhxte.ad.spring.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nxckywhxte.ad.spring.server.entities.UserEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
