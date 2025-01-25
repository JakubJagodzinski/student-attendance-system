package com.attendance_backend.controller;

import com.attendance_backend.model.Attendance;
import com.attendance_backend.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // Dodanie obecności
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Attendance addAttendance(@RequestBody Attendance attendance) {
        return attendanceService.addAttendance(attendance);
    }

    // Pobranie obecności po ID
    @GetMapping("/{attendanceId}")
    public Attendance getAttendanceById(@PathVariable Long attendanceId) {
        return attendanceService.getAttendanceById(attendanceId);
    }

    // Pobranie wszystkich obecności
    @GetMapping
    public List<Attendance> getAllAttendances() {
        return attendanceService.getAllAttendances();
    }

    // Usunięcie obecności
    @DeleteMapping("/{attendanceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAttendance(@PathVariable Long attendanceId) {
        attendanceService.deleteAttendance(attendanceId);
    }

    // Zaktualizowanie obecności
    @PutMapping("/{attendanceId}")
    public Attendance updateAttendance(@PathVariable Long attendanceId, @RequestBody Attendance attendanceDetails) {
        return attendanceService.updateAttendance(attendanceId, attendanceDetails);
    }
}
