package com.attendance_backend.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termId;
    private String termName;
    private Long termGroupId;
    private String termDate;
    private String termStartTime;
    private String termEndTime;

    public Term() {}
}
