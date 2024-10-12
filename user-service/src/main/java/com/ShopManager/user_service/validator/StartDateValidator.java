package com.ShopManager.user_service.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.util.Objects;

public class StartDateValidator implements ConstraintValidator<StartDateConstraint, LocalDate> {

    @Override
    public void initialize(StartDateConstraint startDateStartDateConstraint) {
    }

    @Override
    public boolean isValid(LocalDate startDate, ConstraintValidatorContext context) {
        if (Objects.isNull(startDate)) return true;

        return startDate.isBefore(LocalDate.now());
    }
}
