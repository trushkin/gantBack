package com.example.ganttback.gantt.task;

import com.example.ganttback.gantt.link.GanttLink;
import com.example.ganttback.gantt.link.GanttLinkRepository;
import com.example.ganttback.resource.Resource;
import com.example.ganttback.resource.ResourceRepository;
import com.example.ganttback.user.User;
import com.example.ganttback.user.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import testdata.GanttTaskTemplateData;
import testdata.ResourceTemplateData;
import testdata.UserTemplateData;

import java.time.LocalDate;

@Testcontainers
@SpringBootTest
class GanttTaskRepositoryTest {

    private static Long testUserId = 1L;
    private static Long testResource1Id = 1L;
    private static Long testResource2Id = 2L;
    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.2"));
    @Autowired
    GanttTaskRepository ganttTaskRepository;

    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GanttLinkRepository ganttLinkRepository;

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
        Resource resource1 = ResourceTemplateData.resource1().setUser(testUser).build();
        Resource resource2 = ResourceTemplateData.resource2().setUser(testUser).build();
        resourceRepository.save(resource1);
        resourceRepository.save(resource2);
        testResource1Id = resource1.getId();
        testResource2Id = resource2.getId();
        GanttTask task1 = GanttTaskTemplateData.task1().setUser(testUser).setOwner(resource1).build();
        GanttTask task1_1 = GanttTaskTemplateData.task1_1().setUser(testUser).setOwner(resource1).setParent(task1.getId()).build();
        GanttTask task2 = GanttTaskTemplateData.task2().setUser(testUser).setOwner(resource2).build();
        ganttTaskRepository.save(task1);
        ganttTaskRepository.save(task1_1);
        ganttTaskRepository.save(task2);
        GanttLink testLink = new GanttLink(task1.getId(), task2.getId(), 1L, testUser);
        ganttLinkRepository.save(testLink);
    }

    @AfterEach
    void deleteTestData() {
        ganttLinkRepository.deleteAll();
        ganttTaskRepository.deleteAll();
        resourceRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Tag("Resource")
    void calculateOccupancy() {
        Long actualOccupancy = ganttTaskRepository.calculateOccupancy(testResource2Id);
        Assertions.assertEquals(5L, actualOccupancy);
    }

    @Test
    @Tag("Resource")
    void calculateTotalResourceCost() {
        Long actualTotalResourceCost = ganttTaskRepository.calculateTotalResourceCost(testResource2Id);
        Assertions.assertEquals(225L, actualTotalResourceCost);
    }

    @Test
    @Tag("Report")
    void calculateTimeCosts() {
        Long actualTimeCosts = ganttTaskRepository.calculateTimeCosts(testUserId);
        Assertions.assertEquals(17L, actualTimeCosts);
    }

    @Test
    @Tag("Report")
    void calculateTasksQuantity() {
        Long actualTasksQuantity = ganttTaskRepository.calculateTasksQuantity(testUserId);
        Assertions.assertEquals(3, actualTasksQuantity);
    }

    @Test
    @Tag("Report")
    void calculateTimeSpent() {
        Long actualTimeSpent = ganttTaskRepository.calculateTimeSpent(testUserId);
        Assertions.assertEquals(9L, actualTimeSpent);
    }

    @Test
    @Tag("Report")
    void findProjectStartDate() {
        LocalDate actualProjectStartDate = LocalDate.parse(ganttTaskRepository.findProjectStartDate(testUserId));
        Assertions.assertEquals(LocalDate.of(2024, 2, 25), actualProjectStartDate);
    }
}