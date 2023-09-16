package com.springbot.tttn.infrastructure.controller;

import com.springbot.tttn.application.entities.Subject;
import com.springbot.tttn.domain.payloads.ResponseObject;
import com.springbot.tttn.domain.payloads.Result;
import com.springbot.tttn.domain.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Result> addSubject (@Validated @RequestBody Subject subject) {
        ResponseObject result = subjectService.save(subject);
        return ResponseEntity.status(result.getStatusCode()).body(result.getResult());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateSubject (@Validated @RequestBody Subject newSubject, @PathVariable Long id){
        ResponseObject result = subjectService.update(newSubject, id);
        return ResponseEntity.status(result.getStatusCode()).body(result.getResult());
    }

    @DeleteMapping
    public ResponseEntity<Result> deleteSubject (@RequestParam List<Long> ids){
        ResponseObject result = subjectService.delete(ids);
        return ResponseEntity.status(result.getStatusCode()).body(result.getResult());
    }
}
