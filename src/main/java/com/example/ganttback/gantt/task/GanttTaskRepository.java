package com.example.ganttback.gantt.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GanttTaskRepository extends JpaRepository<GanttTask, Long> {
    List<GanttTask> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);

    Optional<List<GanttTask>> findAllByOwnerId(Long ownerId);

    @Query(nativeQuery = true, value = """
            SELECT SUM(t.duration)
            FROM tasks t
            WHERE t.owner_id = :ownerId
            """)
    Long calculateOccupancy(Long ownerId);

    @Query(nativeQuery = true, value = """
            SELECT SUM(t.duration * r.salary)
            FROM tasks t
            INNER JOIN resources r ON t.owner_id = r.id
            WHERE t.owner_id = :ownerId
            """)
    Long calculateTotalCost(Long ownerId);

    @Query(nativeQuery = true, value = """
                        SELECT SUM(t.duration)
                        FROM tasks t
                        WHERE t.user_id = :userId
            """)
    Long calculateTimeCosts(Long userId);

    @Query(nativeQuery = true, value = """
                    SELECT COUNT(*)
                    FROM tasks t
                    WHERE t.user_id = :userId
            """)
    Long calculateTasksQuantity(Long userId);

    @Query(nativeQuery = true, value = """
                    SELECT SUM(t.progress * t.duration)
                    FROM tasks t
                    WHERE t.user_id = :userId
            """)
    Long calculateTimeSpent(Long userId);

    @Query(nativeQuery = true, value = """
                    SELECT MIN(t.start_date)
                    FROM tasks t
                    WHERE t.user_id = :userId
            """)
    String findProjectStartDate(Long userId);

    @Query(nativeQuery = true, value = """
                SELECT MAX(t.start_date + duration * interval '1 day')
                  FROM tasks t
                    WHERE t.user_id = :userId
            """)
    String findProjectEndDate(Long userId);
}
