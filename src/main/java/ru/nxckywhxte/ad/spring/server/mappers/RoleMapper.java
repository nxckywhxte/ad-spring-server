package ru.nxckywhxte.ad.spring.server.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ru.nxckywhxte.ad.spring.server.dtos.RoleDto;
import ru.nxckywhxte.ad.spring.server.entities.RoleEntity;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto map(RoleEntity roleEntity);
    @InheritInverseConfiguration
    RoleEntity map(RoleDto roleDto);

}
