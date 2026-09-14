package org.user.registration.ms.dto;

import org.user.registration.ms.exception.IllegalBirthdateFormatException;
import org.user.registration.ms.exception.IllegalCountryException;
import org.user.registration.ms.validator.ISO8601Validator;

import java.util.Locale;

public record UserRegistrationRequestDto(
        String username,
        String birthdate,
        String countryOfResidence
) {
    public UserRegistrationRequestDto {
        // Check if the birthdate follows the appropriate ISO-8601 format
        if (!ISO8601Validator.isValidISO8601(birthdate)) {
            throw new IllegalBirthdateFormatException("Birthdate must follow the ISO-8601 format: 'yyyy-mm-dd'");
        }
        // Check if the country corresponds to a valid country code
        if (!Locale.getISOCountries(Locale.IsoCountryCode.PART1_ALPHA3).contains(countryOfResidence)) {
            throw new IllegalCountryException("Country of residence must be a valid 3-letter code (ex: 'USA', 'FRA'");
        }
    }
}
