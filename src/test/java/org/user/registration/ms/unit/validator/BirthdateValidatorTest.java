package org.user.registration.ms.unit.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BirthdateValidatorTest {

    private final BirthdateValidator validator = new BirthdateValidator();

    @Test
    void shouldAcceptValidBirthdate() {
        Assertions.assertTrue(validator.isValid("1998-05-05", null));
    }

    @Test
    void shouldRejectInvalidBirthdateFormat() {
        Assertions.assertFalse(validator.isValid("05/05/1998", null));
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
