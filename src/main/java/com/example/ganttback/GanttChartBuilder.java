package com.example.ganttback;

import java.time.LocalDate;

public class GanttChartBuilder {
    private GanttChart chart;

    public GanttChartBuilder() {
        this.chart = new GanttChart();
    }

    public GanttChartBuilder addTask(Long id, String text, LocalDate startDate, Integer duration, Double progress) {
        GanttTask task = new GanttTask(id, text, startDate, duration, progress);
        this.chart.addTask(task);
        return this;
    }

    public GanttChartBuilder addLink(Long id, Long target, Long source, String type) {
        GanttLink link = new GanttLink(id, target, source, type);
        this.chart.addLink(link);
        return this;
    }

    public GanttChart build() {
        return chart;
    }
}
