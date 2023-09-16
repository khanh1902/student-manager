package com.springbot.tttn.domain.services;

import com.springbot.tttn.application.entities.Subject;
import com.springbot.tttn.domain.payloads.ResponseObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {
    ResponseObject save(Subject subject);
    ResponseObject update(Subject newSubject, Long subjectId);
    ResponseObject delete(List<Long> subjectIds);
}
