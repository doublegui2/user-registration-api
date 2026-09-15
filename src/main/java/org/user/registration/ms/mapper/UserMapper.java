package org.user.registration.ms.mapper;

import org.springframework.stereotype.Component;
import org.user.registration.ms.dto.UserRegistrationRequestDto;
import org.user.registration.ms.dto.UserResponseDto;
import org.user.registration.ms.entity.UserEntity;

@Component
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

    public UserResponseDto toResponseDto(UserEntity entity) {
        return new UserResponseDto(
                entity.getUsername(),
                entity.getBirthdate(),
                entity.getCountryOfResidence(),
                entity.getPhoneNumber(),
                entity.getGender(),
                entity.createdAt()
        );
    }
}
