package ru.nxckywhxte.ad.spring.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nxckywhxte.ad.spring.server.dtos.GroupDto;
import ru.nxckywhxte.ad.spring.server.services.GroupService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class GroupEntityController {
    private final GroupService groupService;

    @PostMapping()
    public GroupDto createGroup(@RequestBody GroupDto groupDto) {
        return groupService.createGroup(groupDto);
    }

    @GetMapping()
    public Collection<GroupDto> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping(value = "/{groupId}")
    public GroupDto getGroupById(@PathVariable String groupId) {
        return groupService.getGroupById(UUID.fromString(groupId));
    }

    @DeleteMapping(value = "/{groupId}")
    public void deleteGroup(@PathVariable String groupId) {
        groupService.removeGroupById(UUID.fromString(groupId));
    }
}
