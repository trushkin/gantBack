package com.example.ganttback;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class GanttTask {
    private Long id;
    private String text;
    @JsonProperty("start_date")
    private LocalDate startDate;
    private Integer duration;
    private Double progress;

    public GanttTask(Long id, String text, LocalDate startDate, Integer duration, Double progress) {
        this.id = id;
        this.text = text;
        this.startDate = startDate;
        this.duration = duration;
        this.progress = progress;
    }

    public Long getId() {
        return id;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }
}
