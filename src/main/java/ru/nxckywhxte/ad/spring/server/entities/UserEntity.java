package ru.nxckywhxte.ad.spring.server.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    @Column(name = "username")
    String username;
    @Column(name = "email")
    String email;
    @Column(name = "hashed_password")
    String hashedPassword;

    @ManyToOne(targetEntity = RoleEntity.class)
    RoleEntity role;

    @ManyToMany()
    Collection<GroupEntity> groups;
}
