package org.user.registration.ms.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.user.registration.ms.validator.CountryValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CountryValidator.class)
public @interface ValidCountry {

    String message() default "country code format not valid, please use ISO-3166 ALPHA3 format 'FRA'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
