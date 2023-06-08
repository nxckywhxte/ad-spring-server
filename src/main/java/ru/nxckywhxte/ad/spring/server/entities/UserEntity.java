package ru.nxckywhxte.ad.spring.server.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    @Column(name = "username")
    String username;
    @Column(name = "email")
    String email;
    @Column(name = "hashed_password")
    String password;

    @ManyToMany()
    Collection<RoleEntity> roles;

    @ManyToMany()
    Collection<GroupEntity> groups;

    @OneToMany(mappedBy = "user")
    private Collection<TokenEntity> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName())).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
