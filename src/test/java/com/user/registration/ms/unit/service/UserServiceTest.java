package com.user.registration.ms.unit.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.user.registration.ms.dto.UserRegistrationRequestDto;
import com.user.registration.ms.dto.UserResponseDto;
import com.user.registration.ms.entity.UserEntity;
import com.user.registration.ms.exception.IllegalAgeException;
import com.user.registration.ms.exception.IllegalCountryException;
import com.user.registration.ms.exception.UserNotFoundException;
import com.user.registration.ms.exception.UsernameAlreadyExistsException;
import com.user.registration.ms.mapper.UserMapper;
import com.user.registration.ms.repository.UserRepository;
import com.user.registration.ms.service.UserService;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreatedUserWithValidInformation() {
        UserRegistrationRequestDto request = new UserRegistrationRequestDto(
                "arthas",
                "1997-07-05",
                "FRA",
                null,
                "MALE"
        );
        UserEntity entity = new UserEntity(
                request.username(),
                request.birthdate(),
                request.countryOfResidence(),
                request.phoneNumber(),
                request.gender()
        );
        UserResponseDto response = new UserResponseDto(
                request.username(),
                request.birthdate(),
                request.countryOfResidence(),
                request.phoneNumber(),
                request.gender(),
                entity.createdAt()
        );
        when(repository.existsByUsername(request.username())).thenReturn(false);
        when(mapper.toEntity(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toResponseDto(entity)).thenReturn(response);

        UserResponseDto result = userService.register(request);

        Assertions.assertEquals(result, response);

        verify(repository).save(entity);
    }

    @Test
    void shouldRejectDuplicateUsername() {
        UserRegistrationRequestDto request = new UserRegistrationRequestDto(
                "arthas",
                "1997-07-05",
                "FRA",
                null,
                "MALE"
        );
        when(repository.existsByUsername(request.username())).thenReturn(true);

        UsernameAlreadyExistsException exception = Assertions.assertThrows(
                UsernameAlreadyExistsException.class,
                () -> userService.register(request)
        );
        Assertions.assertTrue(exception.getMessage().contains("Please choose another username."));
    }

    @Test
    void shouldRejectNonFrenchResident() {
        UserRegistrationRequestDto request = new UserRegistrationRequestDto(
                "arthas",
                "1997-07-05",
                "USA",
                null,
                "MALE"
        );

        IllegalCountryException exception = Assertions.assertThrows(
                IllegalCountryException.class,
                () -> userService.register(request)
        );
        Assertions.assertTrue(exception.getMessage().contains("User must be a French (FRA) resident to register"));
    }

    @Test
    void shouldRejectNonAdult() {
        UserRegistrationRequestDto request = new UserRegistrationRequestDto(
                "arthas",
                "2025-07-05",
                "FRA",
                null,
                "MALE"
        );

        IllegalAgeException exception = Assertions.assertThrows(
                IllegalAgeException.class,
                () -> userService.register(request)
        );
        Assertions.assertTrue(exception.getMessage().contains("User must be at least 18 to register"));
    }

    @Test
    void shouldRetrieveUserByUsername() {
        UserEntity entity = new UserEntity(
                "arthas",
                "1997-07-05",
                "FRA",
                null,
                "MALE"
        );
        UserResponseDto response = new UserResponseDto(
                entity.getUsername(),
                entity.getBirthdate(),
                entity.getCountryOfResidence(),
                entity.getPhoneNumber(),
                entity.getGender(),
                entity.createdAt()
        );
        when(repository.findByUsername("arthas")).thenReturn(Optional.of(entity));
        when(mapper.toResponseDto(entity)).thenReturn(response);

        UserResponseDto result = userService.view("arthas");

        Assertions.assertEquals(result, response);

        verify(repository).findByUsername("arthas");
    }

    @Test
    void shouldThrowExceptionWhenRetrievingUserWithWrongUsername() {
        when(repository.findByUsername("arthas")).thenReturn(Optional.empty());
        UserNotFoundException exception = Assertions.assertThrows(
                UserNotFoundException.class,
                () -> userService.view("arthas")
        );
        Assertions.assertTrue(exception.getMessage().contains("not found"));
    }

    @Test
    void shouldRetrieveUserById() {
        UserEntity entity = new UserEntity(
                "arthas",
                "1997-07-05",
                "FRA",
                null,
                "MALE"
        );
        UserResponseDto response = new UserResponseDto(
                entity.getUsername(),
                entity.getBirthdate(),
                entity.getCountryOfResidence(),
                entity.getPhoneNumber(),
                entity.getGender(),
                entity.createdAt()
        );
        Long id = entity.getId();
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toResponseDto(entity)).thenReturn(response);

        UserResponseDto result = userService.view(id);

        Assertions.assertEquals(result, response);

        verify(repository).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenRetrievingUserWithWrongId() {
        when(repository.findById(42L)).thenReturn(Optional.empty());
        UserNotFoundException exception = Assertions.assertThrows(
                UserNotFoundException.class,
                () -> userService.view(42L)
        );
        Assertions.assertTrue(exception.getMessage().contains("not found"));
    }
}
