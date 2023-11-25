package com.example.ganttback;

public class GanttLink {
    private Long id;
    private Long target;
    private Long source;
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GanttLink(Long id, Long target, Long source, String type) {
        this.id = id;
        this.target = target;
        this.source = source;
        this.type = type;
    }
}
