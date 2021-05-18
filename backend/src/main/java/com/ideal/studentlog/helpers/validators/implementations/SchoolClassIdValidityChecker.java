package com.ideal.studentlog.helpers.validators.implementations;

import com.ideal.studentlog.database.repositories.SchoolClassRepository;
import com.ideal.studentlog.helpers.validators.annotations.ValidSchoolClassId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SchoolClassIdValidityChecker implements ConstraintValidator<ValidSchoolClassId, Integer> {

    @Autowired
    private SchoolClassRepository repository;

    @Override
    public void initialize(ValidSchoolClassId constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return repository.findById(id).isPresent();
    }

}
