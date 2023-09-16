//package com.springbot.tttn.infrastructure.repositories;
//
//import com.springbot.tttn.application.entities.StudentScores;
//import com.springbot.tttn.application.entities.StudentScoresKey;
//import jakarta.transaction.Transactional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.UUID;
//
//@Repository
//public interface StudentScoresRepository extends JpaRepository<StudentScores, StudentScoresKey> {
//
//    @Transactional
//    @Query("SELECT ss FROM StudentScores ss WHERE ss.id.studentId = :studentID AND ss.id.subjectId = :subjectId")
//    StudentScores findById_StudentIdAndId_SubjectId(UUID studentId, Long subjectId);
//
//    @Transactional
//    @Query("SELECT ss FROM StudentScores ss WHERE ss.id.studentId = :studentID")
//    StudentScores findById_StudentId(UUID studentId);
//
//    @Transactional
//    @Query("SELECT ss FROM StudentScores ss WHERE ss.id.subjectId = :subjectId")
//    StudentScores findById_SubjectId(Long subjectId);
//}
