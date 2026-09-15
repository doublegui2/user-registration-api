package org.user.registration.ms.dto;

import jakarta.validation.constraints.NotBlank;
import org.user.registration.ms.annotation.ValidBirthdate;
import org.user.registration.ms.annotation.ValidCountry;

public record UserRegistrationRequestDto(

        @NotBlank
        String username,

        @NotBlank
        @ValidBirthdate
        String birthdate,

        @NotBlank
        @ValidCountry
        String countryOfResidence,

        String phoneNumber,

        String gender
) {}
