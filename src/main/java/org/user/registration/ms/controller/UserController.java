package org.user.registration.ms.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.user.registration.ms.dto.UserRegistrationRequestDto;
import org.user.registration.ms.dto.UserResponseDto;
import org.user.registration.ms.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRegistrationRequestDto requestDto) {
        UserResponseDto userResponseDto = this.userService.register(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponseDto);
    }

    @GetMapping("/view/username/{username}")
    public ResponseEntity<UserResponseDto> viewUser(@PathVariable String username) {
        UserResponseDto userResponseDto = this.userService.view(username);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userResponseDto);
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<UserResponseDto> viewUser(@PathVariable Long id) {
        UserResponseDto userResponseDto = this.userService.view(id);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userResponseDto);
    }

}
