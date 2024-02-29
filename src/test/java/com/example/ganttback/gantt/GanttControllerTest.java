package com.example.ganttback.gantt;

import com.example.ganttback.gantt.link.GanttLink;
import com.example.ganttback.gantt.link.GanttLinkMapper;
import com.example.ganttback.gantt.link.GanttLinkRepository;
import com.example.ganttback.gantt.task.GanttTask;
import com.example.ganttback.gantt.task.GanttTaskMapper;
import com.example.ganttback.gantt.task.GanttTaskRepository;
import com.example.ganttback.resource.Resource;
import com.example.ganttback.resource.ResourceRepository;
import com.example.ganttback.user.User;
import com.example.ganttback.user.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import testdata.GanttChartTemplateData;
import testdata.GanttTaskTemplateData;
import testdata.ResourceTemplateData;
import testdata.UserTemplateData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class GanttControllerTest {
    private static Long testUserId = 1L;

    private GanttChart ganttChart;
    @Autowired
    GanttChartService ganttChartService;
    @Autowired
    GanttTaskRepository ganttTaskRepository;

    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GanttLinkRepository ganttLinkRepository;
    @Autowired
    GanttTaskMapper ganttTaskMapper;
    @Autowired
    GanttLinkMapper ganttLinkMapper;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

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
        User testUser = UserTemplateData.user1();
        userRepository.save(testUser);
        testUserId = testUser.getId();
        Resource resource1 = ResourceTemplateData.resource1()
                .setUser(testUser)
                .build();
        Resource resource2 = ResourceTemplateData.resource2()
                .setUser(testUser)
                .build();
        resourceRepository.save(resource1);
        resourceRepository.save(resource2);
        GanttTask task1 = GanttTaskTemplateData.task1()
                .setUser(testUser)
                .setOwner(resource1)
                .build();
        GanttTask task1_1 = GanttTaskTemplateData.task1_1()
                .setUser(testUser)
                .setOwner(resource1)
                .setParent(task1.getId())
                .build();
        GanttTask task2 = GanttTaskTemplateData.task2()
                .setUser(testUser)
                .setOwner(resource2)
                .build();
        ganttTaskRepository.save(task1);
        ganttTaskRepository.save(task1_1);
        ganttTaskRepository.save(task2);
        GanttLink testLink = new GanttLink(task1.getId(), task2.getId(), 1L, testUser);
        ganttLinkRepository.save(testLink);


        ganttChart = new GanttChartBuilder()
                .setUserId(testUserId)
                .setTasks(Stream.of(task1, task1_1, task2).map(ganttTaskMapper::toGanttChartDto).toList())
                .setLinks(List.of(ganttLinkMapper.toGanttLinkDto(testLink)))
                .build();
        // ganttChartService.saveGanttChart(ganttChart);
    }

    @AfterEach
    void deleteTestData() {
        ganttLinkRepository.deleteAll();
        ganttTaskRepository.deleteAll();
        resourceRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void getChart() throws Exception {
        String expectedJson = objectMapper.writeValueAsString(ganttChart);
        mockMvc.perform(
                        get("http://localhost:8080/gantt//{userId}", testUserId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));
    }

    @Test
    void saveGanttChart() throws Exception {

        mockMvc.perform(
                        post("http://localhost:8080//gantt", testUserId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(ganttChart)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Assertions.assertNotNull(ganttChartService.fetchGanttChart(testUserId));

    }
}