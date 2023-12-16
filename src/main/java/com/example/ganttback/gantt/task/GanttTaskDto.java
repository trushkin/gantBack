package com.example.ganttback.gantt.task;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class GanttTaskDto {
    private Long id;
    private String text;
    @JsonProperty("start_date")
    private LocalDate startDate;
    private Long duration;
    private Double progress;

    @JsonProperty("owner")
    private String resource;

    private String priority;
    private Long userId;
    private Long parent;

    public GanttTaskDto() {
    }

    public GanttTaskDto(Long id, String text, LocalDate startDate, Long duration, Double progress, String resource, String priority, Long userId) {
        this.id = id;
        this.text = text;
        this.startDate = startDate;
        this.duration = duration;
        this.progress = progress;
        this.resource = resource;
        this.priority = priority;
        this.userId = userId;
    }

    public GanttTaskDto(Long id, String text, LocalDate startDate, Long duration, Double progress, String resource, String priority) {
        this.id = id;
        this.text = text;
        this.startDate = startDate;
        this.duration = duration;
        this.progress = progress;
        this.resource = resource;
        this.priority = priority;
    }

    public GanttTaskDto(Long id, String text, LocalDate startDate, Long duration, Double progress, String resource, String priority, Long userId, Long parent) {
        this.id = id;
        this.text = text;
        this.startDate = startDate;
        this.duration = duration;
        this.progress = progress;
        this.resource = resource;
        this.priority = priority;
        this.userId = userId;
        this.parent = parent;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
