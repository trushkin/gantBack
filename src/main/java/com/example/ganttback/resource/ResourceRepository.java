package com.example.ganttback.resource;

import com.example.ganttback.gantt.task.GanttTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findAllByUserId(Long userId);

    Resource findByName(String name);
}
