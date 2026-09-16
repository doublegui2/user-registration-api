package org.user.registration.ms.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.user.registration.ms.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository repository;

    @BeforeEach
    void cleanDatabase() {
        repository.deleteAll();
    }

    @Test
    void shouldRegisterAndPersistUser() throws Exception {
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

        Assertions.assertThat(repository.findByUsername("arthas")).isPresent();
    }

    @Test
    void shouldRejectNonFrenchUser() throws Exception {
        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "username": "arthas",
                            "birthdate": "1997-07-05",
                            "countryOfResidence": "USA",
                            "gender": "MALE"
                        }
                        """))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.code").value("COUNTRY_EXCEPTION"));
    }

    @Test
    void shouldRejectNonAdultUser() throws Exception {
        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "username": "arthas",
                            "birthdate": "2025-07-05",
                            "countryOfResidence": "FRA",
                            "gender": "MALE"
                        }
                        """))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.code").value("AGE_EXCEPTION"));
    }

    @Test
    void shouldRejectTakenUsername() throws Exception {
        String json = """
                        {
                            "username": "arthas",
                            "birthdate": "1997-07-05",
                            "countryOfResidence": "FRA",
                            "gender": "MALE"
                        }
                        """;

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("USERNAME_EXCEPTION"));
    }

    @Test
    void shouldReturnUserInfo() throws Exception {
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

        mockMvc.perform(get("/user/view/username/arthas"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.username").value("arthas"))
                .andExpect(jsonPath("$.birthdate").value("1997-07-05"))
                .andExpect(jsonPath("$.countryOfResidence").value("FRA"))
                .andExpect(jsonPath("$.gender").value("MALE"));
    }

    @Test
    void shouldReturnNotFoundForUnknownUser() throws Exception {
        mockMvc.perform(get("/user/view/username/arthas"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("USER_NOT_FOUND"));
    }

}
