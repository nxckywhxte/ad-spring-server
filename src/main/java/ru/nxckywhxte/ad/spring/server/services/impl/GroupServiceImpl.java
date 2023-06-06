package ru.nxckywhxte.ad.spring.server.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.nxckywhxte.ad.spring.server.dtos.GroupDto;
import ru.nxckywhxte.ad.spring.server.entities.GroupEntity;
import ru.nxckywhxte.ad.spring.server.mappers.GroupMapper;
import ru.nxckywhxte.ad.spring.server.repositories.GroupRepository;
import ru.nxckywhxte.ad.spring.server.services.GroupService;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    @Override
    public Collection<GroupDto> getAllGroups() {
        Collection<GroupEntity> allGroups = groupRepository.findAll();
        return allGroups.stream().map(groupMapper::map).toList();
    }

    @Override
    public GroupDto getGroupById(UUID groupId) {
        GroupEntity existGroup = groupRepository.findGroupEntityById(groupId);
        if (Objects.equals(existGroup, null)) {
            throw new ResponseStatusException(NOT_FOUND, "Группа с такими данными не найдена! Проверьте данные и попробуйте еще раз.");
        }
        return groupMapper.map(existGroup);
    }

    @Override
    public GroupDto getGroupByGroupName(String groupName) {
        GroupEntity existGroup = groupRepository.findGroupEntityByName(groupName);
        if (Objects.equals(existGroup, null)) {
            throw new ResponseStatusException(NOT_FOUND, "Группа с такими данными не найдена! Проверьте данные и попробуйте еще раз.");
        }
        return groupMapper.map(existGroup);
    }

    @Override
    public void removeGroupById(UUID groupId) {
        groupRepository.deleteById(groupId);
    }

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        GroupEntity existsGroup = groupRepository.findGroupEntityByName(groupDto.getName());
        if (Objects.nonNull(existsGroup)) {
            throw new ResponseStatusException(CONFLICT, "Роль с такими данными уже существует! Проверьте данные и попробуйте еще раз.");
        }
        GroupEntity newGroup = GroupEntity.builder()
                .name(groupDto.getName())
                .build();
        groupRepository.saveAndFlush(newGroup);
        return groupMapper.map(newGroup);
    }
}
