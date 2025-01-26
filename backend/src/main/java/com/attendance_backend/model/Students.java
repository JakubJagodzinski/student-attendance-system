package com.attendance_backend.model;


import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Students {
    @Id
    @Column(unique = true, nullable = false)
    private String studentIndex;
    private String studentName;
    private String studentSurname;
    private Long studentGroupId;

    public Students() {}
}
