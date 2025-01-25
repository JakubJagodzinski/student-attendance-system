package com.attendance_backend.service;

import com.attendance_backend.model.StudentsGroup;
import com.attendance_backend.repository.StudentsGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsGroupService {

    private final StudentsGroupRepository studentsGroupRepository;

    public StudentsGroupService(StudentsGroupRepository studentsGroupRepository) {
        this.studentsGroupRepository = studentsGroupRepository;
    }

    // Dodanie grupy
    public StudentsGroup addGroup(StudentsGroup group) {
        return studentsGroupRepository.save(group);
    }

    // Pobranie grupy po ID
    public StudentsGroup getGroupById(Long groupId) {
        Optional<StudentsGroup> group = studentsGroupRepository.findById(groupId);
        return group.orElse(null);
    }

    // Pobranie wszystkich grup
    public List<StudentsGroup> getAllGroups() {
        return studentsGroupRepository.findAll();
    }

    // Usunięcie grupy
    public void deleteGroup(Long groupId) {
        studentsGroupRepository.deleteById(groupId);
    }

    // Zaktualizowanie grupy
    public StudentsGroup updateGroup(Long groupId, StudentsGroup groupDetails) {
        StudentsGroup group = studentsGroupRepository.findById(groupId).orElse(null);
        if (group != null) {
            group.setGroupName(groupDetails.getGroupName());
            group.setGroupDescription(groupDetails.getGroupDescription());
            return studentsGroupRepository.save(group);
        }
        return null;
    }
}
