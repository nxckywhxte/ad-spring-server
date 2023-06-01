package ru.nxckywhxte.ad.spring.server.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ru.nxckywhxte.ad.spring.server.dtos.GroupDto;
import ru.nxckywhxte.ad.spring.server.entities.GroupEntity;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupDto map(GroupEntity groupEntity);

    @InheritInverseConfiguration
    GroupEntity map(GroupDto groupDto);


}
