package com.attendance_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Attendance {

    @Id
    @GeneratedValue
    private Long attendanceID;

    @ManyToOne
    private Students students;

    @ManyToOne
    private Term term;

    private String attendanceStatus;

    public Attendance() {
    }

}
