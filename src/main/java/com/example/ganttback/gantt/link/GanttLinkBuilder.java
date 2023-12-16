package com.example.ganttback.gantt.link;

import com.example.ganttback.user.User;

public class GanttLinkBuilder {
    private final GanttLink ganttLink;

    public GanttLinkBuilder() {
        this.ganttLink = new GanttLink();
    }
    public GanttLinkBuilder setId(Long id){
        this.ganttLink.setId(id);
        return this;
    }
    public GanttLinkBuilder setSource(Long source){
        this.ganttLink.setSource(source);
        return this;
    }
    public GanttLinkBuilder setTarget(Long target){
        this.ganttLink.setTarget(target);
        return this;
    }
    public GanttLinkBuilder setType(Long type){
        this.ganttLink.setType(type);
        return this;
    }
    public GanttLinkBuilder setUser(User user){
        this.ganttLink.setUser(user);
        return this;
    }
    public GanttLink build(){
        return ganttLink;
    }
}
