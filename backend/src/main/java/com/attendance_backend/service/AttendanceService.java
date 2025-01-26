package com.attendance_backend.service;

import com.attendance_backend.model.Attendance;
import com.attendance_backend.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    // Dodanie obecności
    public Attendance addAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    // Pobranie obecności po ID
    public Attendance getAttendanceById(Long attendanceId) {
        Optional<Attendance> attendance = attendanceRepository.findById(attendanceId);
        return attendance.orElse(null);
    }

    // Pobranie wszystkich obecności
    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

    // Usunięcie obecności
    public void deleteAttendance(Long attendanceId) {
        attendanceRepository.deleteById(attendanceId);
    }

    // Zaktualizowanie obecności
    public Attendance updateAttendance(Long attendanceId, Attendance attendanceDetails) {
        Attendance attendance = attendanceRepository.findById(attendanceId).orElse(null);
        if (attendance != null) {
            attendance.setAttendanceStatus(attendanceDetails.getAttendanceStatus());
            attendance.setTermId(attendanceDetails.getTermId());
            attendance.setStudentIndex(attendanceDetails.getStudentIndex());
            return attendanceRepository.save(attendance);
        }
        return null;
    }
}
