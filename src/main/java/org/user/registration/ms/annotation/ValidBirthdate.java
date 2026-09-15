package org.user.registration.ms.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.user.registration.ms.validator.ISO8601Validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ISO8601Validator.class)
public @interface ValidBirthdate {

    String message() default "format not valid, please use ISO-8601 format 'YYYY-MM-DD'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
