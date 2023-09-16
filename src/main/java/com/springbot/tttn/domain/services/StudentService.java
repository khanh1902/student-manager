package com.springbot.tttn.domain.services;

import com.springbot.tttn.application.entities.Student;
import com.springbot.tttn.domain.payloads.ResponseObject;
import com.springbot.tttn.domain.payloads.StudentReq;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface StudentService {
    ResponseObject save(StudentReq student);
    ResponseObject update(StudentReq newStudent, UUID studentId);

    ResponseObject delete(List<UUID> studentId);
}
