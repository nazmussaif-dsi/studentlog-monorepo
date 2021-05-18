package com.ideal.studentlog.helpers.validators.implementations;

import com.ideal.studentlog.database.repositories.ClassDetailsRepository;
import com.ideal.studentlog.helpers.validators.annotations.ValidClassDetailsId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClassDetailsIdValidityChecker implements ConstraintValidator<ValidClassDetailsId, Integer> {

    @Autowired
    private ClassDetailsRepository repository;

    @Override
    public void initialize(ValidClassDetailsId constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return repository.findById(id).isPresent();
    }

}
