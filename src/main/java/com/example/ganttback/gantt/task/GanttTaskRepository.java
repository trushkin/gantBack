package com.example.ganttback.gantt.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GanttTaskRepository extends JpaRepository<GanttTask, Long> {
    List<GanttTask> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
