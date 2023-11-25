package com.example.ganttback;

import java.util.ArrayList;
import java.util.List;

public class GanttChart {
    private List<GanttTask> data;
    private List<GanttLink> links;

    public GanttChart() {
        this.data = new ArrayList<>();
        this.links = new ArrayList<>();
    }

    public void addTask(GanttTask task) {
        this.data.add(task);
    }

    public List<GanttTask> getData() {
        return data;
    }

    public void setData(List<GanttTask> data) {
        this.data = data;
    }

    public List<GanttLink> getLinks() {
        return links;
    }

    public void setLinks(List<GanttLink> links) {
        this.links = links;
    }

    public void addLink(GanttLink link) {
        this.links.add(link);
    }
}
