package com.example.ganttback.gantt;

import java.time.LocalDate;

public class GanttChartBuilder {
    private final GanttChart chart;

    public GanttChartBuilder() {
        this.chart = new GanttChart();
    }

    public GanttChartBuilder addTask(Long id, String text, LocalDate startDate, Long duration, Double progress, String resource, String priority) {
        GanttTaskDto task = new GanttTaskDto(id, text, startDate, duration, progress, resource, priority);
        this.chart.addTask(task);
        return this;
    }

    public GanttChartBuilder addLink(Long id, Long target, Long source, String type) {
        GanttLinkDto link = new GanttLinkDto(id, target, source, type);
        this.chart.addLink(link);
        return this;
    }
    public GanttChartBuilder setUserId(Long userId){
        this.setUserId(userId);
        return this;
    }

    public GanttChart build() {
        return chart;
    }
}
