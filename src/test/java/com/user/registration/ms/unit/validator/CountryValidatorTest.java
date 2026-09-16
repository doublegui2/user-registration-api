package com.user.registration.ms.unit.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.user.registration.ms.validator.CountryValidator;

public class CountryValidatorTest {

    private final CountryValidator validator = new CountryValidator();

    @Test
    void shouldAcceptCorrectCountryCode() {
        Assertions.assertTrue(validator.isValid("FRA", null));
    }

    @Test
    void shouldRejectInvalidCountryCode() {
        Assertions.assertFalse(validator.isValid("TMP", null));
    }

    @Test
    void shouldRejectWrongCountryCodeFormat() {
        Assertions.assertFalse(validator.isValid("FR", null));
    }

    // This test exists because we use @NotBlank on the field to handle blank values, so this validator must not interfere
    @Test
    void shouldAcceptBlankValue() {
        Assertions.assertTrue(validator.isValid("", null));
    }

    // This test exists because we use @NotBlank on the field to handle null values, so this validator must not interfere
    @Test
    void shouldAcceptNullValue() {
        Assertions.assertTrue(validator.isValid(null, null));
    }
}
