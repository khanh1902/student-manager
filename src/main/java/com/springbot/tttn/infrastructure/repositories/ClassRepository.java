package com.springbot.tttn.infrastructure.repositories;

import com.springbot.tttn.application.entities.Class;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    @Transactional
    @Query("SELECT c FROM Class c WHERE c.className = :className")
    Class findByClassName(String className);

    @Transactional
    @Query("SELECT c FROM Class c WHERE c.classId = :classId")
    Class findByClassId(Long classId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Class c WHERE c.classId = :classId")
    void deleteByClassId(Long classId);
}
