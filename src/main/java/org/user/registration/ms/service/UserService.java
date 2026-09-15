package org.user.registration.ms.service;

import org.springframework.stereotype.Service;
import org.user.registration.ms.dto.UserRegistrationRequestDto;
import org.user.registration.ms.dto.UserResponseDto;
import org.user.registration.ms.entity.UserEntity;
import org.user.registration.ms.mapper.UserMapper;
import org.user.registration.ms.repository.UserRepository;

@Service
public class UserService {

    private final UserMapper mapper;

    private UserRepository repository;

    public UserService(UserMapper mapper, UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public UserResponseDto register(UserRegistrationRequestDto registrationRequest) {
        UserEntity entity = this.mapper.toEntity(registrationRequest);
        this.repository.save(entity);
        return this.mapper.toResponseDto(entity);
    }
}
