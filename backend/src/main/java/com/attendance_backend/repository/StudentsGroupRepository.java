package com.attendance_backend.repository;

import com.attendance_backend.model.StudentsGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsGroupRepository extends JpaRepository<StudentsGroup, Long> {
}
