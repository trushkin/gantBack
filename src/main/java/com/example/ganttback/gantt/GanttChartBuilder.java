package com.example.ganttback.gantt;

import com.example.ganttback.gantt.link.GanttLinkDto;
import com.example.ganttback.gantt.task.GanttTaskDto;

import java.time.LocalDate;
import java.util.List;

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

    public GanttChartBuilder addLink(Long id, Long target, Long source, Long type) {
        GanttLinkDto link = new GanttLinkDto(id, target, source, type);
        this.chart.addLink(link);
        return this;
    }
    public GanttChartBuilder setTasks(List<GanttTaskDto> tasks){
        this.chart.setData(tasks);
        return this;
    }
    public GanttChartBuilder setLinks(List<GanttLinkDto> links){
        this.chart.setLinks(links);
        return this;
    }

    public GanttChartBuilder setUserId(Long userId) {
        this.chart.setUserId(userId);
        return this;
    }

    public GanttChart build() {
        return chart;
    }
}
