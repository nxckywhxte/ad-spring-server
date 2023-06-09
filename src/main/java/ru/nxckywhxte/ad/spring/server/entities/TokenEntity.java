package ru.nxckywhxte.ad.spring.server.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nxckywhxte.ad.spring.server.enums.TokenType;

import java.util.UUID;

@Entity
@Table(name = "tokens")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(unique = true)
    public String token;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public UserEntity user;
}
