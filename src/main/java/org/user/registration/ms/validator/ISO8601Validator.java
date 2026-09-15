package org.user.registration.ms.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.user.registration.ms.annotation.ValidBirthdate;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ISO8601Validator implements ConstraintValidator<ValidBirthdate, String> {
    @Override
    public boolean isValid(String dateStr, ConstraintValidatorContext context) {
        if (dateStr == null || dateStr.isBlank()) {
            return true;
        }
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException _) {
            return false;
        }
    }
    public static boolean isAbove18(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            Period period = Period.between(date, LocalDate.now());
            return (period.getYears() >= 18);
        } catch (DateTimeParseException _) {
            return false;
        }
    }
}
