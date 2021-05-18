package com.ideal.studentlog.helpers.validators.implementations;

import com.ideal.studentlog.database.repositories.TestRepository;
import com.ideal.studentlog.helpers.validators.annotations.ValidTestId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TestIdValidityChecker implements ConstraintValidator<ValidTestId, Integer> {

    @Autowired
    private TestRepository repository;

    @Override
    public void initialize(ValidTestId constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return repository.findById(id).isPresent();
    }

}
