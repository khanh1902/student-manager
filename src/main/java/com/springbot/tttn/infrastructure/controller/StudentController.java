package com.springbot.tttn.infrastructure.controller;

import com.springbot.tttn.application.entities.Student;
import com.springbot.tttn.domain.payloads.ResponseObject;
import com.springbot.tttn.domain.payloads.Result;
import com.springbot.tttn.domain.payloads.StudentReq;
import com.springbot.tttn.domain.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Result> addStudent(@Validated @RequestBody StudentReq student) {
        ResponseObject result = studentService.save(student);
        return ResponseEntity.status(result.getStatusCode()).body(result.getResult());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateStudent(@Validated @RequestBody StudentReq newStudent, @PathVariable UUID id) {
        ResponseObject result = studentService.update(newStudent, id);
        return ResponseEntity.status(result.getStatusCode()).body(result.getResult());
    }

    @DeleteMapping
    public ResponseEntity<Result> deleteStudents(@RequestParam(name = "ids") List<UUID> ids) {
        ResponseObject result = studentService.delete(ids);
        return ResponseEntity.status(result.getStatusCode()).body(result.getResult());
    }
}
