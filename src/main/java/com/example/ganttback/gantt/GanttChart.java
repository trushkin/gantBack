package com.example.ganttback.gantt;

import com.example.ganttback.gantt.link.GanttLinkDto;
import com.example.ganttback.gantt.task.GanttTaskDto;

import java.util.ArrayList;
import java.util.List;

public class GanttChart {
    private List<GanttTaskDto> data;
    private List<GanttLinkDto> links;

    private Long userId;

    public GanttChart() {
        this.data = new ArrayList<>();
        this.links = new ArrayList<>();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void addTask(GanttTaskDto task) {
        this.data.add(task);
    }

    public List<GanttTaskDto> getData() {
        return data;
    }

    public void setData(List<GanttTaskDto> data) {
        this.data = data;
    }

    public List<GanttLinkDto> getLinks() {
        return links;
    }

    public void setLinks(List<GanttLinkDto> links) {
        this.links = links;
    }

    public void addLink(GanttLinkDto link) {
        this.links.add(link);
    }
}
