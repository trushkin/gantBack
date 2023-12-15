package com.example.ganttback.resource;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findAllByUserId(Long userId);
}
