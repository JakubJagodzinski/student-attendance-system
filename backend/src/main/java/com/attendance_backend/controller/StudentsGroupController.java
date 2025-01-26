package com.attendance_backend.controller;

import com.attendance_backend.model.StudentsGroup;
import com.attendance_backend.service.StudentsGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentsGroupController {

    private final StudentsGroupService studentsGroupService;

    public StudentsGroupController(StudentsGroupService studentsGroupService) {
        this.studentsGroupService = studentsGroupService;
    }

    // Dodanie grupy
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentsGroup addGroup(@RequestBody StudentsGroup group) {
        return studentsGroupService.addGroup(group);
    }

    // Pobranie grupy po ID
    @GetMapping("/{groupId}")
    public StudentsGroup getGroupById(@PathVariable Long groupId) {
        return studentsGroupService.getGroupById(groupId);
    }

    // Pobranie wszystkich grup
    @GetMapping
    public List<StudentsGroup> getAllGroups() {
        return studentsGroupService.getAllGroups();
    }

    // Usunięcie grupy
    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(@PathVariable Long groupId) {
        studentsGroupService.deleteGroup(groupId);
    }

    // Zaktualizowanie grupy
    @PutMapping("/{groupId}")
    public StudentsGroup updateGroup(@PathVariable Long groupId, @RequestBody StudentsGroup groupDetails) {
        return studentsGroupService.updateGroup(groupId, groupDetails);
    }
}
