package org.user.registration.ms.dto;

import jakarta.validation.constraints.NotBlank;
import org.user.registration.ms.annotation.ValidBirthdate;
import org.user.registration.ms.annotation.ValidCountry;

public record UserRegistrationRequestDto(

        @NotBlank(message = "must not be blank")
        String username,

        @NotBlank(message = "must not be blank")
        @ValidBirthdate
        String birthdate,

        @NotBlank(message = "must not be blank")
        @ValidCountry
        String countryOfResidence,

        String phoneNumber,

        String gender
) {}
