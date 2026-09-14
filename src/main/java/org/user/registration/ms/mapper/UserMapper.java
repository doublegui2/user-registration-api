package org.user.registration.ms.mapper;

import org.user.registration.ms.dto.UserRegistrationRequestDto;
import org.user.registration.ms.entity.UserEntity;

public class UserMapper {

    public UserEntity toEntity(UserRegistrationRequestDto dto) {
        return new UserEntity(
                dto.username(),
                dto.birthdate(),
                dto.countryOfResidence(),
                dto.phoneNumber(),
                dto.gender()
        );
    }
}
