package org.user.registration.ms.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.user.registration.ms.dto.UserRegistrationRequestDto;
import org.user.registration.ms.dto.UserResponseDto;
import org.user.registration.ms.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRegistrationRequestDto requestDto) {
        UserResponseDto userResponseDto = this.userService.register(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponseDto);
    }

}
