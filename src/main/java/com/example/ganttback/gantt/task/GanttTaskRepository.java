package com.example.ganttback.gantt.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GanttTaskRepository extends JpaRepository<GanttTask, Long> {
    List<GanttTask> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);
    Optional<List<GanttTask>> findAllByOwnerId(Long ownerId);
}
