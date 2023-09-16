package com.springbot.tttn.domain.services.impl;

import com.springbot.tttn.application.entities.Class;
import com.springbot.tttn.domain.payloads.ResponseObject;
import com.springbot.tttn.domain.payloads.Result;
import com.springbot.tttn.domain.services.ClassService;
import com.springbot.tttn.infrastructure.repositories.ClassRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;
    private final Logger logger = LoggerFactory.getLogger(ClassServiceImpl.class);

    @Override
    public ResponseObject findAll() {
        List<Class> findAll = classRepository.findAll();
        return new ResponseObject(HttpStatus.OK, new Result("Save class successfully", findAll));

    }

    @Override
    public ResponseObject save(Class class_) {
        logger.info("Action: Add new class");
        Class isClass = classRepository.findByClassName(class_.getClassName());
        if (isClass != null) {
            logger.info("Error: Class already exists");
            return new ResponseObject(HttpStatus.BAD_REQUEST, new Result("Class already exists", null));
        }
        logger.info("Create new class: " + class_.toString());
        Class createClass = classRepository.save(class_);
        return new ResponseObject(HttpStatus.CREATED, new Result("Save class successfully", createClass));
    }

    @Override
    public ResponseObject update(Class newClass, Long classId) {
        logger.info("Action: Update class");

        Class isClassName = classRepository.findByClassName(newClass.getClassName());
        if (isClassName != null) {
            logger.info("Error: Class name" + newClass.getClassName() + "already exists");
            return new ResponseObject(HttpStatus.BAD_REQUEST, new Result("Class name already exists", null));
        }

        Class isClass = classRepository.findByClassId(classId);
        if (isClass == null) {
            logger.info("Create new class: " + newClass.toString());
            return new ResponseObject(HttpStatus.CREATED, new Result("Save class successfully", classRepository.save(newClass)));
        }


        logger.info("Update class " + isClass.toString() + " to " + newClass.toString());
        isClass.setClassName(newClass.getClassName());
        isClass.setSchoolYear(newClass.getSchoolYear());
        return new ResponseObject(HttpStatus.OK, new Result("Update class successfully", classRepository.save(isClass)));
    }

    @Override
    public ResponseObject delete(List<Long> classIds) {
        logger.info("Action: Delete class");
        if (classIds.size() < 2) {
            Class isClass = classRepository.findByClassId(classIds.get(0));
            logger.info("Delete class: " + isClass.toString());
            classRepository.deleteByClassId(classIds.get(0));
            return new ResponseObject(HttpStatus.OK, new Result("Delete class successfully", null));
        }
        boolean isDelete = false;
        for (Long classId : classIds) {
            Class isClass = classRepository.findByClassId(classId);
            if (isClass == null) {
                logger.info("Error: Class id " + classId + " does not exists to delete");
                continue;
            }
            isDelete = true;
            logger.info("Delete class: " + isClass.toString());
            classRepository.deleteByClassId(classId);
        }
        if (!isDelete) {
            return new ResponseObject(HttpStatus.BAD_REQUEST, new Result("Error: Classes does not exists to delete", null));
        }
        return new ResponseObject(HttpStatus.OK, new Result("Delete class successfully", null));
    }
}
