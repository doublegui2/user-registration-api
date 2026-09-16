package com.user.registration.ms.unit.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.user.registration.ms.controller.UserController;
import com.user.registration.ms.dto.UserRegistrationRequestDto;
import com.user.registration.ms.dto.UserResponseDto;
import com.user.registration.ms.service.UserService;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.time.Instant;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void shouldReturnCreatedUserIsRegistered() throws Exception {
        UserResponseDto response = new UserResponseDto(
                "arthas",
                "1997-07-05",
                "FRA",
                null,
                "MALE",
                Instant.now()
        );
        when(userService.register(any(UserRegistrationRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "username": "arthas",
                            "birthdate": "1997-07-05",
                            "countryOfResidence": "FRA",
                            "gender": "MALE"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("arthas"))
                .andExpect(jsonPath("$.birthdate").value("1997-07-05"))
                .andExpect(jsonPath("$.countryOfResidence").value("FRA"))
                .andExpect(jsonPath("$.gender").value("MALE"));
    }

    @Test
    void shouldReturnBadRequestForInvalidRequest() throws Exception {
        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "username": "",
                            "birthdate": "1997-07-05",
                            "countryOfResidence": "FRA"
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "username": "arthas",
                            "birthdate": "",
                            "countryOfResidence": "FRA"
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "username": "arthas",
                            "birthdate": "1997-07-05",
                            "countryOfResidence": ""
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
    }

    @Test
    void shouldReturnUserByUsername() throws Exception {
        UserResponseDto response = new UserResponseDto(
                "arthas",
                "1997-07-05",
                "FRA",
                null,
                "MALE",
                Instant.now()
        );
        when(userService.view("arthas"))
                .thenReturn(response);

        mockMvc.perform(get("/user/view/username/arthas"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.username").value("arthas"));
    }
}
