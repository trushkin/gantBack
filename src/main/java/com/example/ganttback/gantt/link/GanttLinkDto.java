package com.example.ganttback.gantt.link;

public class GanttLinkDto {
    private Long id;
    private Long target;
    private Long source;
    private Long type;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public GanttLinkDto() {
    }

    public GanttLinkDto(Long id, Long target, Long source, Long type) {
        this.id = id;
        this.target = target;
        this.source = source;
        this.type = type;
    }

    public GanttLinkDto(Long id, Long target, Long source, Long type, Long userId) {
        this.id = id;
        this.target = target;
        this.source = source;
        this.type = type;
        this.userId = userId;
    }
}
