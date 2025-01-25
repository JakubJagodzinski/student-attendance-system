package com.attendance_backend.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
public class StudentsGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    private String groupName;
    private String groupDescription;

    @OneToMany(mappedBy = "studentsGroup")
    private List<Students> students;  // Lista studentów w grupie (relacja one-to-many)

    public StudentsGroup() {

    }
}
