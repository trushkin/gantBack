package com.example.ganttback.resource;

import com.example.ganttback.ApiResponse;
import com.example.ganttback.user.User;
import com.example.ganttback.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import testdata.ResourceTemplateData;
import testdata.UserTemplateData;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class ResourceControllerTest {

    private static Long testUserId = 1L;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ResourceMapper resourceMapper;
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

    private Resource resource1;
    private Resource resource2;

    @BeforeEach
    void insertTestData() {
        User testUser = UserTemplateData.user1();
        userRepository.save(testUser);
        testUserId = testUser.getId();
        resource1 = ResourceTemplateData.resource1().setUser(testUser).build();
        resource2 = ResourceTemplateData.resource2().setUser(testUser).build();
        resourceRepository.save(resource1);
        resourceRepository.save(resource2);
    }

    @AfterEach
    void deleteTestData() {
        resourceRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void getResources() throws Exception {
        List<Resource> resourceTestList = List.of(resource1, resource2);
        ApiResponse<List<ResourceDto>> expectedApiResponse = new ApiResponse<>(HttpStatus.OK.value(),
                "Resources fetched successfully", resourceTestList.stream().map(resourceMapper::toResourceDto).toList());
        String expectedJson = objectMapper.writeValueAsString(expectedApiResponse);
        mockMvc.perform(
                        get("http://localhost:8080/resources/{userId}", testUserId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));
    }


    @Test
    void getResourcesToDropdown() throws Exception {
        List<Resource> resourceTestList = List.of(resource1, resource2);
        List<ResourceDropdownDto> resourceDropdownDtoList = new ArrayList<>();
        for (ResourceDto temp : resourceTestList.stream().map(resourceMapper::toResourceDto).toList()) {
            resourceDropdownDtoList.add(new ResourceDropdownDto(temp.getName(), temp.getName()));
        }
        ApiResponse<List<ResourceDropdownDto>> expectedApiResponse = new ApiResponse<>(HttpStatus.OK.value(), "Resources fetched successfully",
                resourceDropdownDtoList);
        String expectedJson = objectMapper.writeValueAsString(expectedApiResponse);
        mockMvc.perform(
                        get("http://localhost:8080/resources/resource-dropdown/{userId}", testUserId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));
    }

    @Test
    void saveResource() throws Exception {
        resourceRepository.deleteAll();
        mockMvc.perform(
                        post("http://localhost:8080/resources/{userId}", testUserId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(resource1)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertFalse(resourceRepository.findAll().isEmpty());
    }

    @Test
    void deleteResource() throws Exception {
        mockMvc.perform(
                        delete("http://localhost:8080/resources/{resourceId}", resource1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Assertions.assertFalse(resourceRepository.findById(resource1.getId()).isPresent());
    }

    @Test
    void updateResource() throws Exception {
        resource1.setName("UpdatedName");
        mockMvc.perform(
                        put("http://localhost:8080/resources/{resourceId}", resource1.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(resource1)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Resource updatedResource = resourceRepository.findByName(resource1.getName());
        Assertions.assertEquals(updatedResource.getName(), resource1.getName());
    }


    @Test
    void getResourceById() throws Exception {
        ApiResponse<ResourceDto> expectedApiResponse = new ApiResponse<>(HttpStatus.OK.value(),
                "Resource fetch successfully", resourceMapper.toResourceDto(resource2));
        String expectedJson = objectMapper.writeValueAsString(expectedApiResponse);
        mockMvc.perform(
                        get("http://localhost:8080/resources/get-one/{resourceId}", resource2.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));
    }
}