package com.ideal.studentlog.helpers.validators.implementations;

import com.ideal.studentlog.database.repositories.SubjectRepository;
import com.ideal.studentlog.helpers.validators.annotations.ValidSubjectId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SubjectIdValidityChecker implements ConstraintValidator<ValidSubjectId, Integer> {

    @Autowired
    private SubjectRepository repository;

    @Override
    public void initialize(ValidSubjectId constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return repository.findById(id).isPresent();
    }

}
