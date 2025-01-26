package com.attendance_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Attendance {

    @Id
    @GeneratedValue
    private Long attendanceId;
    private String studentIndex;
    private Long groupId;
    private Long termId;
    private String attendanceStatus;

    public Attendance() {
    }

}
