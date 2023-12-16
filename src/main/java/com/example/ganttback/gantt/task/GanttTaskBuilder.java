package com.example.ganttback.gantt.task;

import com.example.ganttback.resource.Resource;
import com.example.ganttback.user.User;

import java.time.LocalDate;

public class GanttTaskBuilder {
    private final GanttTask ganttTask;

    public GanttTaskBuilder() {
        this.ganttTask = new GanttTask();
    }

    public GanttTaskBuilder setId(Long id) {
        this.ganttTask.setId(id);
        return this;
    }

    public GanttTaskBuilder setText(String text) {
        this.ganttTask.setText(text);
        return this;
    }

    public GanttTaskBuilder setStartDate(LocalDate startDate) {
        this.ganttTask.setStartDate(startDate);
        return this;
    }

    public GanttTaskBuilder setPriority(String priority) {
        this.ganttTask.setPriority(priority);
        return this;
    }

    public GanttTaskBuilder setDuration(Long duration) {
        this.ganttTask.setDuration(duration);
        return this;
    }

    public GanttTaskBuilder setProgress(Double progress) {
        this.ganttTask.setProgress(progress);
        return this;
    }

    public GanttTaskBuilder setOwner(Resource owner) {
        this.ganttTask.setOwner(owner);
        return this;
    }

    public GanttTaskBuilder setUser(User user) {
        this.ganttTask.setUser(user);
        return this;
    }
    public GanttTaskBuilder setParent(Long parent){
        this.ganttTask.setParent(parent);
        return this;
    }

    public GanttTask build() {
        return ganttTask;
    }

}
