package org.user.registration.ms.dto;

import jakarta.validation.constraints.NotBlank;
import org.user.registration.ms.annotation.ValidBirthdate;
import org.user.registration.ms.annotation.ValidCountry;
import org.user.registration.ms.validator.ISO8601Validator;

import java.time.Instant;
import java.util.Locale;

public record UserResponseDto(
        @NotBlank
        String username,

        @NotBlank
        @ValidBirthdate
        String birthdate,

        @NotBlank
        @ValidCountry
        String countryOfResidence,

        String phoneNumber,

        String gender,

        @NotBlank
        Instant createdAt
) {
    public UserResponseDto {
        // Check if the country corresponds to a valid country code
        if (!Locale.getISOCountries(Locale.IsoCountryCode.PART1_ALPHA3).contains(countryOfResidence)) {
            throw new IllegalArgumentException("Country of residence must be a valid 3-letter code (ex: 'USA', 'FRA'");
        }
    }
}
