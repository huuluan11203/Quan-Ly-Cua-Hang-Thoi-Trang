package com.ShopManager.user_service.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {StartDateValidator.class})
public @interface StartDateConstraint {

    String message() default "Start date invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
