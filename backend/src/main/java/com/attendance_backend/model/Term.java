package com.attendance_backend.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termID;
    private String date;
    private String startTime;
    private String endTime;

    @ManyToOne
    private StudentsGroup studentsGroup;

    public Term() {}
}
