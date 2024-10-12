package com.ShopManager.user_service.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;


public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String> {

    private static final String PHONE_NUMBER_REGEX = "^(0|84)" +
            "(2(0[3-9]|1[0-689]|2[0-25-9]|3[2-9]|4[0-9]|5[124-9]|6[0369]|7[0-7]|8[0-9]|9[012346789])" +
            "|3[2-9]|5[25689]|7[06-9]|8[0-9]|9[012346789])([0-9]{7})$";

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        if (phoneNumber == null) {
            return false;
        }
        return Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber);
    }

    @Override
    public void initialize(PhoneNumberConstraint phoneNumberConstraint) {
    }
}
