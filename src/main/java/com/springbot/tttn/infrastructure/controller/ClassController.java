package com.springbot.tttn.infrastructure.controller;

import com.springbot.tttn.application.entities.Class;
import com.springbot.tttn.domain.payloads.ResponseObject;
import com.springbot.tttn.domain.payloads.Result;
import com.springbot.tttn.domain.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<Result> findAllClasses() {
        ResponseObject result = classService.findAll();
        return ResponseEntity.status(result.getStatusCode()).body(result.getResult());
    }

    @PostMapping
    public ResponseEntity<Result> addClass(@Validated @RequestBody Class aClass) {
        ResponseObject result = classService.save(aClass);
        return ResponseEntity.status(result.getStatusCode()).body(result.getResult());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateClass(@Validated @RequestBody Class newClass, @PathVariable Long id) {
        ResponseObject result = classService.update(newClass, id);
        return ResponseEntity.status(result.getStatusCode()).body(result.getResult());
    }

    @DeleteMapping
    public ResponseEntity<Result> deleteClass(@RequestParam(name = "ids") List<Long> ids) {
        ResponseObject result = classService.delete(ids);
        return ResponseEntity.status(result.getStatusCode()).body(result.getResult());
    }
}
