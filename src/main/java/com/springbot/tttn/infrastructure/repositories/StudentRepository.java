package com.springbot.tttn.infrastructure.repositories;

import com.springbot.tttn.application.entities.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    @Transactional
    @Query("SELECT st FROM Student st WHERE st.studentId = :studentId")
    Student findByStudentId(UUID studentId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Student st WHERE st.studentId = :studentId")
    void deleteByStudentId(UUID studentId);
}
