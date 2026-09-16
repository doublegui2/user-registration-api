package com.user.registration.ms.mapper;

import org.springframework.stereotype.Component;
import com.user.registration.ms.dto.UserRegistrationRequestDto;
import com.user.registration.ms.dto.UserResponseDto;
import com.user.registration.ms.entity.UserEntity;

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
