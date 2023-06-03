package ru.nxckywhxte.ad.spring.server.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.nxckywhxte.ad.spring.server.entities.GroupEntity;
import ru.nxckywhxte.ad.spring.server.repositories.GroupRepository;
import ru.nxckywhxte.ad.spring.server.services.GroupService;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    @Override
    public Collection<GroupEntity> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public GroupEntity getGroupById(UUID groupId) {
        GroupEntity existGroup = groupRepository.findGroupEntityById(groupId);
        if (Objects.equals(existGroup, null)) {
            throw new ResponseStatusException(NOT_FOUND, "Группа с такими данными не найдена! Проверьте данные и попробуйте еще раз.");
        }
        return existGroup;
    }

    @Override
    public GroupEntity getGroupByGroupName(String groupName) {
        GroupEntity existGroup = groupRepository.findGroupEntityByName(groupName);
        if (Objects.equals(existGroup, null)) {
            throw new ResponseStatusException(NOT_FOUND, "Группа с такими данными не найдена! Проверьте данные и попробуйте еще раз.");
        }
        return existGroup;
    }

    @Override
    public void removeGroupById(UUID groupId) {
        groupRepository.deleteById(groupId);
    }

    @Override
    public GroupEntity createGroup(GroupEntity groupEntity) {
        GroupEntity existsGroup = groupRepository.findGroupEntityByName(groupEntity.getName());
        if (Objects.nonNull(existsGroup)) {
            throw new ResponseStatusException(CONFLICT, "Роль с такими данными уже существует! Проверьте данные и попробуйте еще раз.");
        }
        GroupEntity newGroup = GroupEntity.builder()
                .name(groupEntity.getName())
                .build();
        groupRepository.saveAndFlush(newGroup);
        return newGroup;
    }
}
