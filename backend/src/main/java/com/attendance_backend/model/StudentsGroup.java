package com.attendance_backend.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class StudentsGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    private String groupName;
    private String groupDescription;

    public StudentsGroup() {

    }
}
