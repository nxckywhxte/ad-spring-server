package ru.nxckywhxte.ad.spring.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nxckywhxte.ad.spring.server.dtos.GroupDto;
import ru.nxckywhxte.ad.spring.server.entities.GroupEntity;
import ru.nxckywhxte.ad.spring.server.mappers.GroupMapper;
import ru.nxckywhxte.ad.spring.server.services.impl.GroupServiceImpl;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupEntityController {
    private final GroupMapper groupMapper;
    private final GroupServiceImpl groupService;

    @PostMapping()
    public GroupDto createGroup(@RequestBody GroupDto groupDto) {
        GroupEntity groupEntity = groupMapper.map(groupDto);
        GroupEntity newGroupEntity = groupService.createGroup(groupEntity);
        return groupMapper.map(newGroupEntity);
    }

    @GetMapping()
    public Collection<GroupDto> getAllGroups() {
        Collection<GroupEntity> allGroups = groupService.getAllGroups();
        return allGroups.stream().map(groupMapper::map).toList();
    }

    @GetMapping(value = "/{groupId}")
    public GroupDto getGroupById(@PathVariable String groupId) {
        GroupEntity groupEntity = groupService.getGroupById(UUID.fromString(groupId));
        return groupMapper.map(groupEntity);
    }

    @DeleteMapping(value = "/{groupId}")
    public void deleteGroup(@PathVariable String groupId) {
        groupService.removeGroupById(UUID.fromString(groupId));
    }
}
