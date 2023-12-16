package com.example.ganttback.gantt.task;

import java.time.LocalDate;

public class GanttTaskDtoBuilder {
    private final GanttTaskDto ganttTaskDto;

    public GanttTaskDtoBuilder() {
        this.ganttTaskDto = new GanttTaskDto();
    }

    public GanttTaskDtoBuilder setId(Long id) {
        this.ganttTaskDto.setId(id);
        return this;
    }

    public GanttTaskDtoBuilder setText(String text) {
        this.ganttTaskDto.setText(text);
        return this;
    }

    public GanttTaskDtoBuilder setStartDate(LocalDate startDate) {
        this.ganttTaskDto.setStartDate(startDate);
        return this;
    }

    public GanttTaskDtoBuilder setDuration(Long duration) {
        this.ganttTaskDto.setDuration(duration);
        return this;
    }

    public GanttTaskDtoBuilder setProgress(Double progress) {
        this.ganttTaskDto.setProgress(progress);
        return this;
    }

    public GanttTaskDtoBuilder setResource(String owner) {
        this.ganttTaskDto.setResource(owner);
        return this;
    }

    public GanttTaskDtoBuilder setPriority(String priority) {
        this.ganttTaskDto.setPriority(priority);
        return this;
    }

    public GanttTaskDtoBuilder setUserId(Long userId) {
        this.ganttTaskDto.setUserId(userId);
        return this;
    }
    public GanttTaskDtoBuilder setParent(Long parent){
        this.ganttTaskDto.setParent(parent);
        return this;
    }

    public GanttTaskDto build() {
        return ganttTaskDto;
    }
}
