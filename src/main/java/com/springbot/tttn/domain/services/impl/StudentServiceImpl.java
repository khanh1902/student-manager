package com.springbot.tttn.domain.services.impl;

import com.springbot.tttn.application.entities.Class;
import com.springbot.tttn.application.entities.Student;
import com.springbot.tttn.domain.payloads.ResponseObject;
import com.springbot.tttn.domain.payloads.Result;
import com.springbot.tttn.domain.payloads.StudentReq;
import com.springbot.tttn.domain.services.StudentService;
import com.springbot.tttn.infrastructure.repositories.ClassRepository;
import com.springbot.tttn.infrastructure.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    private Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public ResponseObject save(StudentReq student) {
        logger.info("Action: Create new student");
        Class isClass = classRepository.findByClassName(student.getClassName());
        if (isClass == null) {
            logger.info("Error: Class " + student.getClassName() + " does not exists");
            return new ResponseObject(HttpStatus.BAD_REQUEST, new Result("Class " + student.getClassName() + " does not exists", null));
        }
        Student createStudent = studentRepository.save(new Student(student.getStudentName(), student.getStudentAddress(), isClass));
        logger.info("New student: " + createStudent.toString());
        return new ResponseObject(HttpStatus.CREATED, new Result("Save student successfully", createStudent));

    }

    @Override
    public ResponseObject update(StudentReq newStudent, UUID studentId) {
        logger.info("Action: Update Student");
        // check class update exists
        Class isClass = classRepository.findByClassName(newStudent.getClassName());
        if (isClass == null) {
            logger.info("Error: Class does not exists to update");
            return new ResponseObject(HttpStatus.BAD_REQUEST, new Result("Class does not exists to update", null));
        }
        Student isStudent = studentRepository.findByStudentId(studentId);
        if (isStudent == null) {
            logger.info("Add new Student: " + newStudent.toString());
            Student createStudent = new Student(newStudent.getStudentName(), newStudent.getStudentAddress(), isClass);
            return new ResponseObject(HttpStatus.CREATED, new Result("Save student successfully", studentRepository.save(createStudent)));
        }

        logger.info("Update Student from " + isStudent.toString() + " to " + new Student(newStudent.getStudentName(), newStudent.getStudentAddress(), isClass));
        isStudent.setStudentAddress(newStudent.getStudentAddress());
        isStudent.setStudentName(newStudent.getStudentName());
        isStudent.setClass_(isClass);
        return new ResponseObject(HttpStatus.OK, new Result("Update student successfully", studentRepository.save(isStudent)));
    }

    @Override
    public ResponseObject delete(List<UUID> studentIds) {
        logger.info("Action: Delete Student");
        if (studentIds.size() < 2) {
            Student isStudent = studentRepository.findByStudentId(studentIds.get(0));
            logger.info("Delete student: " + isStudent.toString());
            studentRepository.deleteByStudentId(studentIds.get(0));
            return new ResponseObject(HttpStatus.OK, new Result("Delete student successfully", null));
        }
        boolean isDelete = false;
        for (UUID studentId : studentIds) {
            Student isStudent = studentRepository.findByStudentId(studentId);
            if (isStudent == null) {
                logger.info("Error: Student id" + studentId + " does not exists to delete");
                continue;
            }
            isDelete = true;
            logger.info("Delete student: " + isStudent.toString());
            studentRepository.deleteByStudentId(studentId);
        }
        if(!isDelete) {
            return new ResponseObject(HttpStatus.BAD_REQUEST, new Result("Error: Students does not exists to delete", null));
        }
        return new ResponseObject(HttpStatus.OK, new Result("Delete student successfully", null));
    }
}
