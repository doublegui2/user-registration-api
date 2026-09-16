package org.user.registration.ms.unit.service;

import org.springframework.stereotype.Service;
import org.user.registration.ms.dto.UserRegistrationRequestDto;
import org.user.registration.ms.dto.UserResponseDto;
import org.user.registration.ms.entity.UserEntity;
import org.user.registration.ms.exception.IllegalAgeException;
import org.user.registration.ms.exception.IllegalCountryException;
import org.user.registration.ms.exception.UserNotFoundException;
import org.user.registration.ms.exception.UsernameAlreadyExistsException;
import org.user.registration.ms.mapper.UserMapper;
import org.user.registration.ms.repository.UserRepository;
import org.user.registration.ms.unit.validator.BirthdateValidator;

@Service
public class UserService {

    private final UserMapper mapper;

    private final UserRepository repository;

    public UserService(UserMapper mapper, UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public UserResponseDto register(UserRegistrationRequestDto registrationRequest) {
        // Check first if the request is from an adult (18+) French resident
        if (!BirthdateValidator.isAbove18(registrationRequest.birthdate())) {
            throw new IllegalAgeException("User must be at least 18 to register");
        }
        if (!registrationRequest.countryOfResidence().equalsIgnoreCase("FRA")) {
            throw new IllegalCountryException("User must be a French (FRA) resident to register");
        }
        // Check if username already exists
        if (repository.existsByUsername(registrationRequest.username())) {
            throw new UsernameAlreadyExistsException(registrationRequest.username());
        }
        UserEntity entity = this.mapper.toEntity(registrationRequest);
        this.repository.save(entity);
        return this.mapper.toResponseDto(entity);
    }

    public UserResponseDto view(String username) {
        UserEntity entity = this.repository
                .findByUsername(username)
                .orElseThrow(() ->
                     new UserNotFoundException(username)
                );
        return this.mapper.toResponseDto(entity);
    }

    public UserResponseDto view(Long id) {
        UserEntity entity = this.repository
                .findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(id)
                );
        return this.mapper.toResponseDto(entity);
    }
}
