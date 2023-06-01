package ru.nxckywhxte.ad.spring.server.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ru.nxckywhxte.ad.spring.server.dtos.UserDto;
import ru.nxckywhxte.ad.spring.server.entities.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity map(UserDto userDto);
}
