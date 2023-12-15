package com.example.ganttback.gantt;

import java.util.ArrayList;
import java.util.List;

public class GanttChart {
    private List<GanttTaskDto> data;
    private List<GanttLinkDto> links;

    public GanttChart() {
        this.data = new ArrayList<>();
        this.links = new ArrayList<>();
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
