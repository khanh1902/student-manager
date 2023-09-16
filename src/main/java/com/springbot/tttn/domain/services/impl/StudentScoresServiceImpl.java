//package com.springbot.tttn.domain.services.impl;
//
//import com.springbot.tttn.application.entities.StudentScores;
//import com.springbot.tttn.domain.payloads.ResponseObject;
//import com.springbot.tttn.domain.payloads.Result;
//import com.springbot.tttn.domain.services.StudentScoresService;
//import com.springbot.tttn.infrastructure.repositories.StudentScoresRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Service
//public class StudentScoresServiceImpl implements StudentScoresService {
//    @Autowired
//    private StudentScoresRepository studentScoresRepository;
//
//    private Logger logger = LoggerFactory.getLogger(StudentScoresServiceImpl.class);
//
//    @Override
//    public ResponseObject save(StudentScores studentScores) {
//        logger.info("Action: Add new student scores");
//        StudentScores isStudentScores = studentScoresRepository.findById_StudentIdAndId_SubjectId(
//                studentScores.getId().getStudentId(),
//                studentScores.getId().getSubjectId());
//
//        if(isStudentScores != null) {
//            logger.info("Error: Student score already exists");
//            return new ResponseObject(HttpStatus.BAD_REQUEST, new Result("Student score already exists", null));
//        }
//        StudentScores createStudentScores = studentScoresRepository.save(studentScores);
//        logger.info("New student score: " + createStudentScores.toString());
//        return new ResponseObject(HttpStatus.CREATED, new Result("Add student scores successfully", createStudentScores));
//    }
//
//    @Override
//    public ResponseObject update(StudentScores newStudentScores, UUID studentId, Long subjectId) {
//
//        return null;
//    }
//}
