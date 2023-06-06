package ru.nxckywhxte.ad.spring.server.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import ru.nxckywhxte.ad.spring.server.entities.GroupEntity;
import ru.nxckywhxte.ad.spring.server.entities.RoleEntity;

import java.util.Collection;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDto {
    private String username;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String rawPassword;
    private Collection<RoleEntity> roles;
    private Collection<GroupEntity> groups;
}
