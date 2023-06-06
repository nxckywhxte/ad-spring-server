package ru.nxckywhxte.ad.spring.server.services;

import org.springframework.stereotype.Service;
import ru.nxckywhxte.ad.spring.server.dtos.GroupDto;
import ru.nxckywhxte.ad.spring.server.entities.GroupEntity;

import java.util.Collection;
import java.util.UUID;
@Service
public interface GroupService {
    public Collection<GroupDto> getAllGroups();
    public GroupDto getGroupById(UUID groupId);
    public GroupDto getGroupByGroupName(String groupName);
    public void removeGroupById(UUID groupId);
    public GroupDto createGroup(GroupDto groupDto);
}
