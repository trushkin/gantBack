package com.example.ganttback.gantt;

import java.time.LocalDate;

public class GanttChartBuilder {
    private GanttChart chart;

    public GanttChartBuilder() {
        this.chart = new GanttChart();
    }

    public GanttChartBuilder addTask(Long id, String text, LocalDate startDate, Long duration, Double progress) {
        GanttTaskDto task = new GanttTaskDto(id, text, startDate, duration, progress);
        this.chart.addTask(task);
        return this;
    }

    public GanttChartBuilder addLink(Long id, Long target, Long source, String type) {
        GanttLinkDto link = new GanttLinkDto(id, target, source, type);
        this.chart.addLink(link);
        return this;
    }

    public GanttChart build() {
        return chart;
    }
}
