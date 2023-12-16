package com.example.ganttback.gantt.link;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GanttLinkRepository extends JpaRepository<GanttLink, Long> {
    List<GanttLink> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
