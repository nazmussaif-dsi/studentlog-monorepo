package com.ideal.studentlog.helpers.validators.implementations;

import com.ideal.studentlog.database.repositories.TeacherRepository;
import com.ideal.studentlog.helpers.validators.annotations.ValidTeacherId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TeacherIdValidityChecker implements ConstraintValidator<ValidTeacherId, Integer> {

    @Autowired
    private TeacherRepository repository;

    @Override
    public void initialize(ValidTeacherId constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return repository.findById(id).isPresent();
    }

}
