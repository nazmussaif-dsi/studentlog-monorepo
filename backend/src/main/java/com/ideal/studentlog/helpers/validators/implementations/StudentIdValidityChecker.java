package com.ideal.studentlog.helpers.validators.implementations;

import com.ideal.studentlog.database.repositories.StudentRepository;
import com.ideal.studentlog.helpers.validators.annotations.ValidStudentId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentIdValidityChecker implements ConstraintValidator<ValidStudentId, Integer> {

    @Autowired
    private StudentRepository repository;

    @Override
    public void initialize(ValidStudentId constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return repository.findById(id).isPresent();
    }

}
