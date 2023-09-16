package com.springbot.tttn.infrastructure.repositories;

import com.springbot.tttn.application.entities.Subject;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Transactional
    @Query("SELECT s FROM Subject s WHERE s.subjectName = :subjectName")
    Subject findBySubjectName(String subjectName);

    @Transactional
    @Query("SELECT s FROM Subject s WHERE s.subjectId = :subjectId")
    Subject findBySubjectId(Long subjectId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Subject c WHERE c.subjectId = :subjectId")
    void deleteBySubjectId(Long subjectId);
}
