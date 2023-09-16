package com.springbot.tttn.domain.services.impl;

import com.springbot.tttn.application.entities.Subject;
import com.springbot.tttn.domain.payloads.ResponseObject;
import com.springbot.tttn.domain.payloads.Result;
import com.springbot.tttn.domain.services.SubjectService;
import com.springbot.tttn.infrastructure.repositories.SubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    private Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Override
    public ResponseObject save(Subject subject) {
        logger.info("Action: Add new Subject");
        Subject isSubject = subjectRepository.findBySubjectName(subject.getSubjectName());
        if (isSubject != null) {
            logger.info("Error: Subject already exists");
            return new ResponseObject(HttpStatus.BAD_REQUEST, new Result("Subject already exists", null));
        }

        Subject createSubject = subjectRepository.save(subject);
        logger.info("New Subject: " + createSubject.toString());

        return new ResponseObject(HttpStatus.CREATED, new Result("Add subject successfully", createSubject));
    }

    @Override
    public ResponseObject update(Subject newSubject, Long subjectId) {
        logger.info("Action: Add new Subject");
        Subject isSubjectName = subjectRepository.findBySubjectName(newSubject.getSubjectName());
        if (isSubjectName != null) {
            logger.info("Error: Subject " + newSubject.getSubjectName() + " already exists");
            return new ResponseObject(HttpStatus.BAD_REQUEST, new Result("Subject already exists", null));
        }

        Subject isSubject = subjectRepository.findBySubjectId(subjectId);
        if (isSubject == null) {
            Subject createSubject = subjectRepository.save(newSubject);
            logger.info("New Subject: " + createSubject.toString());
            return new ResponseObject(HttpStatus.CREATED, new Result("Add subject successfully", createSubject));
        }

        logger.info("Update subject from " + isSubject.toString() + " to " + newSubject.toString());
        isSubject.setSubjectName(newSubject.getSubjectName());
        isSubject.setCredit(newSubject.getCredit());

        return new ResponseObject(HttpStatus.OK, new Result("Update subject successfully", subjectRepository.save(isSubject)));
    }

    @Override
    public ResponseObject delete(List<Long> subjectIds) {
        logger.info("Action: Delete subject");
        if (subjectIds.size() < 2) {
            Subject isSubject = subjectRepository.findBySubjectId(subjectIds.get(0));
            logger.info("Delete subject: " + isSubject.toString());
            subjectRepository.delete(isSubject);
        }
        boolean isDelete = false;
        for (Long subjectId : subjectIds) {
            Subject isSubject = subjectRepository.findBySubjectId(subjectId);
            if (isSubject == null) {
                logger.info("Error: Subject id " + subjectId + " does not exists to delete");
                continue;
            }
            isDelete = true;
            logger.info("Delete class: " + isSubject.toString());
            subjectRepository.deleteBySubjectId(subjectId);
        }
        if(!isDelete) {
            return new ResponseObject(HttpStatus.BAD_REQUEST, new Result("Subjects does not exists to delete", null));
        }
        return new ResponseObject(HttpStatus.OK, new Result("Delete subject successfully", null));
    }
}
