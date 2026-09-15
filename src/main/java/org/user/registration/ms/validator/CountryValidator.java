package org.user.registration.ms.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.user.registration.ms.annotation.ValidCountry;

import java.util.Locale;

public class CountryValidator implements ConstraintValidator<ValidCountry, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Locale.getISOCountries(Locale.IsoCountryCode.PART1_ALPHA3).contains(value);
    }
}
