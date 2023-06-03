package ru.nxckywhxte.ad.spring.server.services;

import ru.nxckywhxte.ad.spring.server.entities.GroupEntity;

import java.util.Collection;
import java.util.UUID;

public interface GroupService {
    public Collection<GroupEntity> getAllGroups();
    public GroupEntity getGroupById(UUID groupId);
    public GroupEntity getGroupByGroupName(String groupName);
    public void removeGroupById(UUID groupId);
    public GroupEntity createGroup(GroupEntity groupEntity);
}
