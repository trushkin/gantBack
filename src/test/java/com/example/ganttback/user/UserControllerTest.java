package com.example.ganttback.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import testdata.UserTemplateData;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    private static final Long INVALID_LOGIN_RESPONSE = -1L;
    private static final Long VALID_LOGIN_RESPONSE = 1L;
    private static final String VALID_PASSWORD = "temppassword";
    private static final String INVALID_PASSWORD = "invalidPassword";
    private User testUser;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.2"));

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.jpa.generate-ddl", () -> true);
    }

    @BeforeEach
    void insertTestData() {
        testUser = new UserTemplateData().user1();
        userRepository.save(testUser);
    }

    @AfterEach
    void deleteTestData() {
        userRepository.deleteAll();
    }

    @Test
    void valid_login_test() throws Exception {
        UserDto testUserDto = new UserDto(testUser.getEmail(), VALID_PASSWORD);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8080/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testUserDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(VALID_LOGIN_RESPONSE.toString()));
    }

    @Test
    void invalid_login_test() throws Exception {
        UserDto testUserDto = new UserDto(testUser.getEmail(), INVALID_PASSWORD);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8080/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testUserDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(INVALID_LOGIN_RESPONSE.toString()));
    }
}